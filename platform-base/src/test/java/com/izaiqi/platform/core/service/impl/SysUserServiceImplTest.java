package com.izaiqi.platform.core.service.impl;

import com.github.pagehelper.Page;
import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.BaseServiceTest;
import com.izaiqi.platform.core.service.SysUserService;
import com.izaiqi.platform.core.util.EncryptPwdUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SysUserServiceImplTest extends BaseServiceTest {

    @Resource
    SysUserService sysUserService;

    @Test
    public void delUser() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);

            Assert.assertNotNull(sysUser);
            sysUserService.delUser(sysUser.getUserId());
            sysUser = sysUserService.getUser(userName);
            Assert.assertEquals(Const.STATE_X, sysUser.getState());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("删除用户异常: " + e.getCode());
        }
    }

    @Test
    public void addUser() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNull(sysUser);

            // 新建用户
            sysUser = new SysUser();
            sysUser.setUserName(userName);
            sysUser.setPassword("123456");
            sysUserService.addUser(sysUser);

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("添加用户异常: " + e.getCode());
        }
    }

    @Test
    public void updateUser() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNotNull(sysUser);

            if (Const.STATE_A.equals(sysUser.getState())) {
                sysUser.setState(Const.STATE_X);
            } else if (Const.STATE_X.equals(sysUser.getState())) {
                sysUser.setState(Const.STATE_A);
            }

            sysUserService.updateUser(sysUser);

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("更新用户异常: " + e.getCode());
        }
    }

    @Test
    public void resetPwd() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNotNull(sysUser);

            String salt = sysUser.getSalt();
            sysUserService.resetPwd(sysUser.getUserId());

            sysUser = sysUserService.getUser(userName);
            Assert.assertEquals(salt, sysUser.getSalt());

            EncryptPwdUtil.Encrypt encrypt = EncryptPwdUtil.encrypt(userName, salt);

            Assert.assertEquals(encrypt.getEncrypt(), sysUser.getPassword());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("重置密码异常: " + e.getCode());
        }
    }

    @Test
    public void changePwd() {
        try {
            String userName = "13819493701";
            String newPassword = "123456";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNotNull(sysUser);

            String salt = sysUser.getSalt();
            sysUserService.resetPwd(sysUser.getUserId());
            sysUserService.changePwd(sysUser.getUserId(), userName, newPassword);

            sysUser = sysUserService.getUser(userName);
            Assert.assertEquals(salt, sysUser.getSalt());

            EncryptPwdUtil.Encrypt encrypt = EncryptPwdUtil.encrypt(newPassword, salt);

            Assert.assertEquals(encrypt.getEncrypt(), sysUser.getPassword());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("修改密码异常: " + e.getCode());
        }
    }

    @Test
    public void lockUser() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNotNull(sysUser);

            if (!Const.YES.equals(sysUser.getIsLocked())) {
                sysUserService.lockUser(sysUser.getUserId(), 1L);
            }
            sysUser = sysUserService.getUser(sysUser.getUserId());
            Assert.assertEquals(Const.YES, sysUser.getIsLocked());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("锁定用户异常: " + e.getCode());
        }
    }

    @Test
    public void unlockUser() {
        try {
            String userName = "13819493701";
            SysUser sysUser = sysUserService.getUser(userName);
            Assert.assertNotNull(sysUser);

            if (!Const.NO.equals(sysUser.getIsLocked())) {
                sysUserService.unlockUser(sysUser.getUserId());
            }
            sysUser = sysUserService.getUser(sysUser.getUserId());
            Assert.assertEquals(Const.NO, sysUser.getIsLocked());

        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("解锁用户异常: " + e.getCode());
        }
    }


    @Test
    public void queryUsers() {
        try {
            // 分页查询
            List<SysUser> sysUserList = sysUserService.queryUsers(1, 10);
            Page page = (Page) sysUserList;
            assertThat(page.getResult().size(), equalTo(2));
            assertThat(page.getTotal(), equalTo(2L));

            // 第2页数据
            sysUserList = sysUserService.queryUsers(2, 10);
            page = (Page) sysUserList;
            assertThat(page.getResult().size(), equalTo(2));
            assertThat(page.getPageNum(), equalTo(1));
            assertThat(page.getTotal(), equalTo(2L));
        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("查询用户异常: " + e.getCode());
        }
    }

    @Test
    public void queryUsersByRoleId() {
        try {
            // 查询角色用户
            List<SysUser> sysUserList = sysUserService.queryUsersByRoleId(1L);
            assertThat(sysUserList.size(), equalTo(1));

            // 查询角色用户
            sysUserList = sysUserService.queryUsersByRoleId(2L);
            assertThat(sysUserList.size(), equalTo(1));

            // 查询角色用户
            sysUserList = sysUserService.queryUsersByRoleId(3L);
            assertThat(sysUserList.size(), equalTo(1));
        } catch (BaseAppException e) {
            e.printStackTrace();
            Assert.fail("查询用户异常: " + e.getCode());
        }
    }

}
