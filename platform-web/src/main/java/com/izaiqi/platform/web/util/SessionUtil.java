package com.izaiqi.platform.web.util;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.domain.model.SysUserSession;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.web.common.Error;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Session Util
 *
 * @author deng.qiming
 * @date 2017-01-10 17:46
 */
public class SessionUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static SysUser getSessionUser() throws BaseAppException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);

        if (userObj != null) {
            return (SysUser) userObj;
        } else {
            throw new BaseAppException(Error.USER_SESSION_TIMEOUT.getCodeString());
        }
    }

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return session;
    }

    public static void setSessionUser(SysUser userObj, SysUserSession userSessionObj) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute(Const.SESSION_LOGIN_USER, userObj);
        session.setAttribute(Const.SESSION_LOGIN_USER_SESSION, userSessionObj);
    }
}
