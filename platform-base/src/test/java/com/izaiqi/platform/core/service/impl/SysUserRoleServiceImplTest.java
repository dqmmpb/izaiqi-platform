package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.ConstTest;
import com.izaiqi.platform.core.domain.model.SysRole;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.BaseServiceTest;
import com.izaiqi.platform.core.service.SysRoleService;
import com.izaiqi.platform.core.service.SysUserRoleService;
import com.izaiqi.platform.core.service.SysUserService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class SysUserRoleServiceImplTest extends BaseServiceTest {

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysUserService sysUserService;

    @Resource
    SysUserRoleService sysUserRoleService;

    @Test
    public void addUserRole() {
        try {
            SysRole sysRole = sysRoleService.getRole(ConstTest.ROLES[0][0]);
            SysUser sysUser = sysUserService.getUser(1L);

            sysUserRoleService.addUserRole(sysUser.getUserId(), sysRole.getRoleId());

            sysRole = sysRoleService.getRole(ConstTest.ROLES[1][0]);
            sysUser = sysUserService.getUser(2L);

            sysUserRoleService.addUserRole(sysUser.getUserId(), sysRole.getRoleId());

            sysRole = sysRoleService.getRole(ConstTest.ROLES[2][0]);
            sysUser = sysUserService.getUser(2L);

            sysUserRoleService.addUserRole(sysUser.getUserId(), sysRole.getRoleId());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("分配角色异常: " + e.getCode());
        }
    }
}
