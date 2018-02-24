package com.izaiqi.platform.core.service;

import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;

/**
 * 登录服务
 *
 * @author deng.qiming
 * @date 2016-12-26 10:05
 */
public interface SysLoginService {

    /**
     * 登录服务
     *
     * @param userName
     * @param pwd
     * @return
     * @throws BaseAppException
     */
    SysUser login(String userName, String pwd) throws BaseAppException;


    /**
     * 注销接口
     *
     * @param loginUser 登录用户
     * @throws BaseAppException
     */
    void logout(SysUser loginUser) throws BaseAppException;
}
