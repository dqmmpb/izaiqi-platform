package com.izaiqi.platform.core.service;

import com.izaiqi.platform.core.domain.model.SysRolePriv;
import com.izaiqi.platform.core.exception.BaseAppException;

import java.util.List;

/**
 * SysRolePriv Service
 *
 * @author deng.qiming
 * @date 2016-12-25 15:51
 */
public interface SysRolePrivService {
    /**
     * 添加角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int addRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 批量添加角色和权限
     *
     * @param rolePrivs
     * @return
     * @throws BaseAppException
     */
    int addRolePriv(List<SysRolePriv> rolePrivs) throws BaseAppException;

    /**
     * 禁用角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int disableRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 启用角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int enableRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 删除角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int delRolePriv(Long roleId, Long privId) throws BaseAppException;
}
