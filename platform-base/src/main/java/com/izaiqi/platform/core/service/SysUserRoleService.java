package com.izaiqi.platform.core.service;

import com.izaiqi.platform.core.domain.model.SysUserRole;
import com.izaiqi.platform.core.exception.BaseAppException;

import java.util.List;

/**
 * 模块名
 *
 * @author deng.qiming
 * @date 2016-12-22 15:30
 */
public interface SysUserRoleService {

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int addUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 批量添加用户角色
     *
     * @param userroles
     * @return
     * @throws BaseAppException
     */
    int addUserRole(List<SysUserRole> userroles) throws BaseAppException;

    /**
     * 禁用用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int disableUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 启用用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int enableUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 删除用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int delUserRole(Long userId, Long roleId) throws BaseAppException;

}
