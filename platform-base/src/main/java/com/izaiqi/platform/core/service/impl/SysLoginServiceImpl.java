package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysLoginService;
import com.izaiqi.platform.core.service.SysUserService;
import com.izaiqi.platform.core.util.EqualsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Login Module
 *
 * @author deng.qiming
 * @date 2016-12-26 10:06
 */
@Service("sysLoginService")
@Transactional(readOnly = true)
public class SysLoginServiceImpl extends BaseService implements SysLoginService {

    @Resource
    private SysUserService sysUserService;

    @Override
    @Transactional(readOnly = false)
    public SysUser login(String userName, String pwd) throws BaseAppException {
        //TODO  校验UserCode

        SysUser sysUser = sysUserService.getUser(userName);
        if (sysUser == null) {
            logger.debug("sysUser [{}] not exist", userName);
            ExceptionHandler.publish(ErrorCode.USER_NAME_OR_PWD_ERROR);
        }

        if (!EqualsUtil.equals(Const.STATE_A, sysUser.getState())) {
            logger.debug("sysUser [{}] not exist", userName);
            ExceptionHandler.publish(ErrorCode.USER_NAME_OR_PWD_ERROR);
        }

        logger.debug("user={}", sysUser);

        SysUser newUser = new SysUser();
        newUser.setUserId(sysUser.getUserId());

        // 判断用户状态
        if (EqualsUtil.equals(sysUser.getIsLocked(), Const.YES)) {
            logger.debug("user has been locked");
            ExceptionHandler.publish(ErrorCode.USER_HAS_LOCKED);
        }

        // 验证密码
        if (sysUserService.validatePwd(pwd, sysUser.getSalt(), sysUser.getPassword())) {
            //清空尝试次数
            sysUserService.resetLoginFail(sysUser.getUserId());
        } else {
            logger.debug("sysUser[{}] pwd is wrong", sysUser.getUserName());
            ExceptionHandler.publish(ErrorCode.USER_NAME_OR_PWD_ERROR);
        }

        //TODO 其他校验

        return sysUser;
    }

    @Override
    @Transactional(readOnly = false)
    public void logout(SysUser loginUser) throws BaseAppException {
        if (loginUser == null) {
            logger.warn("login user is null.");
            return;
        }
    }
}
