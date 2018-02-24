package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.ConstTest;
import com.izaiqi.platform.core.domain.model.SysPriv;
import com.izaiqi.platform.core.domain.model.SysRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.BaseServiceTest;
import com.izaiqi.platform.core.service.SysPrivService;
import com.izaiqi.platform.core.service.SysRolePrivService;
import com.izaiqi.platform.core.service.SysRoleService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class SysRolePrivServiceImplTest extends BaseServiceTest {

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysPrivService sysPrivService;

    @Resource
    SysRolePrivService sysRolePrivService;

    @Test
    public void addRolePriv() {
        try {
            // 超级管理员，分配所有权限
            SysRole sysRole = sysRoleService.getRole(ConstTest.ROLES[0][0]);
            for (String[] priv : ConstTest.PRIVS) {
                System.out.println(priv[1]);
                SysPriv sysPriv = sysPrivService.getPriv(priv[1]);
                sysRolePrivService.addRolePriv(sysRole.getRoleId(), sysPriv.getPrivId());
            }

            // 管理员，分配用户管理权限
            sysRole = sysRoleService.getRole(ConstTest.ROLES[1][0]);

            for (String[] priv : ConstTest.PRIVS) {
                // 正则表达是过滤掉某些权限
                if (priv[1].matches("sys:((profile)|(user)):.*")) {
                    System.out.println(priv[1]);
                    SysPriv sysPriv = sysPrivService.getPriv(priv[1]);
                    sysRolePrivService.addRolePriv(sysRole.getRoleId(), sysPriv.getPrivId());
                }

            }

            // 普通用户，分配查看个人信息和修改密码权限
            sysRole = sysRoleService.getRole(ConstTest.ROLES[2][0]);

            for (String[] priv : ConstTest.PRIVS) {
                // 过滤掉某些权限
                if (priv[1].matches("sys:profile:.*")) {
                    System.out.println(priv[1]);
                    SysPriv sysPriv = sysPrivService.getPriv(priv[1]);
                    sysRolePrivService.addRolePriv(sysRole.getRoleId(), sysPriv.getPrivId());
                }

            }
        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("分配权限异常: " + e.getCode());
        }
    }
}
