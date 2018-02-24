package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.dao.mapper.ext.SysUserRoleExtMapper;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysUserRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统用户角色关联
 *
 * @author deng.qiming
 * @date 2016-12-25 13:02
 */
@Service
@Transactional(readOnly = true)
public class SysUserRoleServiceImpl extends BaseService implements SysUserRoleService {

    @Resource
    private SysUserRoleExtMapper sysUserRoleExtMapper;

    @Override
    @Transactional(readOnly = false)
    public int addUserRole(Long userId, Long roleId) throws BaseAppException {
        //判断是否已经绑定
        boolean exist = sysUserRoleExtMapper.checkUserRoleExist(userId, roleId);
        if (exist) {
            ExceptionHandler.publish(ErrorCode.USERROLE_HAS_EXIST.getCodeString());
        }

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setState(Const.STATE_A);

        Date now = new Date();
        sysUserRole.setCreateTime(now);
        sysUserRole.setUpdateTime(now);

        return sysUserRoleExtMapper.insert(sysUserRole);
    }

    @Override
    public int addUserRole(List<SysUserRole> userroles) throws BaseAppException {
        //TODO 如果有则忽略；如果没有则添加
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int disableUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("disableUserRole userId={},roleId={},state={}", userId, roleId, Const.STATE_X);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setState(Const.STATE_X);
        return sysUserRoleExtMapper.updateByPrimaryKeySelective(sysUserRole);
    }

    @Override
    @Transactional(readOnly = false)
    public int enableUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("disableUserRole userId={},roleId={},state={}", userId, roleId, Const.STATE_A);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setState(Const.STATE_A);
        return sysUserRoleExtMapper.updateByPrimaryKeySelective(sysUserRole);
    }

    @Override
    @Transactional(readOnly = false)
    public int delUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("delete user role userId={},roleId={}", userId, roleId);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        return sysUserRoleExtMapper.deleteByPrimaryKey(sysUserRole);
    }

}
