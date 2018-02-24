package com.izaiqi.platform.web.exception;

import com.alibaba.fastjson.JSONObject;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.util.ExceptionUtil;
import com.izaiqi.platform.core.util.StringUtil;
import com.izaiqi.platform.web.common.Error;
import com.izaiqi.platform.web.util.I18NUtil;
import com.izaiqi.platform.web.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BaseAppException.class)
    public void handleBaseAppException(HttpServletRequest request, HttpServletResponse response, BaseAppException e) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", false);
        jsonObj.put("errorCode", e.getCode());

        String desc = e.getDesc();
        if (StringUtil.isEmpty(desc)) {
            desc = I18NUtil.getMessage(e.getCode());
        }
        jsonObj.put("errorMessage", desc);
        ResponseUtils.renderJson2(request, response, jsonObj, true);
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException be) {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException be) {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(value = NoSuchRequestHandlingMethodException.class)
    public ResponseEntity<?> handleNoSuchRequestHandlingMethodException(NoSuchRequestHandlingMethodException be) {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResult> handleBindException(BindException e) {
        BaseResult result = new BaseResult(false, null);
        List<ObjectError> errors = e.getAllErrors();
        Assert.notEmpty(errors, Error.ERROR_SYS_EXCEPTION.getCodeString());
        ObjectError error = e.getAllErrors().get(0);
        result.setErrorCode("1096");
        result.setErrorMessage(error.getDefaultMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
        MissingServletRequestParameterException.class, MissingServletRequestPartException.class,
        TypeMismatchException.class, ServletRequestBindingException.class})
    public ResponseEntity<BaseResult> handleExceptions(Exception e) {
        return new ResponseEntity<>(buildBaseResult(Error.ERROR_INVALID_REQUEST, e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {ConversionNotSupportedException.class, HttpMessageNotWritableException.class, Exception.class})
    public ResponseEntity<BaseResult> handleException(Exception e) {
        ExceptionUtil.logError(logger, "Unexpected exceptions!!!", e);
        return new ResponseEntity<>(buildBaseResult(Error.ERROR_SYS_EXCEPTION, null),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BaseResult buildBaseResult(Error error, String errorMsg) {
        return this.buildBaseResult(error.getCodeString(), errorMsg);
    }

    private BaseResult buildBaseResult(String errorCode, String errorMsg) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        if (errorMsg == null) {
            String errorMessage = I18NUtil.getMessage("" + errorCode);
            result.setErrorMessage(errorMessage);
        } else {
            result.setErrorMessage(errorMsg);
        }
        return result;
    }

}
