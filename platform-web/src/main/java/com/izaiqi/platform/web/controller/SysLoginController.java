package com.izaiqi.platform.web.controller;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.domain.model.SysUserSession;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.SysLoginService;
import com.izaiqi.platform.core.service.SysUserSessionService;
import com.izaiqi.platform.web.result.model.LoginModel;
import com.izaiqi.platform.web.util.I18NUtil;
import com.izaiqi.platform.web.util.RequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录相关
 *
 * @author deng.qiming
 * @date 2016年11月8日 上午10:26:57
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends BaseController {

    @Resource
    private SysUserSessionService sysUserSessionService;

    @Resource
    private SysLoginService sysLoginService;

    /**
     * 判断是否登陆
     *
     * @param request  req
     * @param response resp
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public BaseResult isLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseParam param) throws BaseAppException {

        BaseResult result = new BaseResult();
        LoginModel loginModel = new LoginModel();


        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Const.SESSION_LOGIN_USER);

        if (obj == null) {
            loginModel.setLogin(false);
        } else {
            SysUser sysUser = (SysUser) obj;
            loginModel.setLogin(true);
            loginModel.setUserName(sysUser.getUserName());
            loginModel.setToken(session.getId());
        }

        result.setResult(loginModel);

        return result;
    }

    /**
     * 登录
     *
     * @param request  req
     * @param response resp
     * @param param param
     * @return
     * @throws BaseAppException
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String userName = (String) params.get("userName");
        String password = (String) params.get("password");

        SysUser sysUser = sysLoginService.login(userName, password);

        BaseResult result = new BaseResult();
        LoginModel loginModel = new LoginModel();

        HttpSession session = request.getSession();
        logger.debug("sessionId={}", session.getId());


        if (sysUser == null) {
            logger.debug("sysUser is invalid, please check!");
            loginModel.setLogin(false);
            result.setErrorCode(ErrorCode.USER_NOT_LOGIN.getCodeString());
            result.setErrorMessage(I18NUtil.getMessage(ErrorCode.USER_NOT_LOGIN.getCodeString()));
        } else {
            logger.debug("sysUser is valid, then will generator token");

            String ua = RequestUtils.getHeader(request, Const.HTTP_HEADER_USER_AGENT, "No User Agent");
            SysUserSession sysUserSession = sysUserSessionService.addSession(sysUser.getUserId(), sysUser.getUserName(), ua, session.getId());

            session.setAttribute(Const.SESSION_LOGIN_USER, sysUser);
            session.setAttribute(Const.SESSION_LOGIN_USER_SESSION, sysUserSession);

            loginModel.setLogin(true);
            loginModel.setUserName(sysUser.getUserName());
            loginModel.setToken(session.getId());
        }

        result.setResult(loginModel);

        return result;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResult logout(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseParam param) throws BaseAppException {
        logger.debug("logout begin");
        HttpSession session = request.getSession();

        if (session != null) {
            Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);
            if (userObj != null) {
                sysLoginService.logout((SysUser) userObj);

                session.removeAttribute(Const.SESSION_LOGIN_USER);
                session.removeAttribute(Const.SESSION_LOGIN_USER_SESSION);
                session.invalidate();
            }
        }
        return new BaseResult(true, null);
    }

}
