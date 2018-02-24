package com.izaiqi.platform.web.aop;

import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.PrivType;
import com.izaiqi.platform.core.domain.model.SysPriv;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysPrivService;
import com.izaiqi.platform.core.util.EqualsUtil;
import com.izaiqi.platform.core.util.ListUtil;
import com.izaiqi.platform.web.common.Error;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 模块名
 *
 * @author deng.qiming
 * @date 2017-01-10 15:06
 */
public class UserPrivAspect {

    private static Logger logger = LoggerFactory.getLogger(UserPrivAspect.class);

    @Autowired
    private SysPrivService sysPrivService;

    public void before(JoinPoint joinPoint) throws Throwable {

        logger.debug("check user priv....");
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);

        if (requiresPermissions != null) {
            String permissions = requiresPermissions.value();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();

            Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);
            if (userObj == null) {
                logger.error("session user is null, plz check!");
                ExceptionHandler.publish(Error.USER_SESSION_TIMEOUT.getCodeString());
            }

            SysUser sysUser = (SysUser) userObj;
            if (ObjectUtils.isEmpty(sysUser) || ObjectUtils.isEmpty(sysUser.getUserId())) {
                logger.error("user info is null, plz check");
                ExceptionHandler.publish(Error.USER_SESSION_TIMEOUT.getCodeString());
            }

            //用户权限列表
            List<SysPriv> sysPrivs = sysPrivService.queryPrivsByUserId(sysUser.getUserId(), PrivType.DATA.getValue());
            //TODO maybe cache is nice.

            Boolean hit = false;
            if (ListUtil.isNotEmpty(sysPrivs)) {
                for (SysPriv sysPriv : sysPrivs) {
                    if (EqualsUtil.equals(sysPriv.getPrivCode(), permissions)) {
                        hit = true;
                        break;
                    }
                }
            }

            if (!hit) {
                ExceptionHandler.publish(com.izaiqi.platform.core.common.ErrorCode.NO_PERMISSION);
            }
        } else {
            logger.warn("There is no @RequiresPermissions in method. Please check.");
        }
    }

}
