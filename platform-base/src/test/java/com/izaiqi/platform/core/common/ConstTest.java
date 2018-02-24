package com.izaiqi.platform.core.common;

public class ConstTest {

    public final static String[][] PRIVS = {
        {"DIR", "sys:priv:dir:manage", "权限管理", "权限管理", "/priv", "/priv"},
        {"MENU", "sys:priv:url:add", "新建权限", "新建权限", "/priv/add", "/priv/add", "sys:priv:dir:manage"},
        {"MENU", "sys:priv:url:edit", "编辑权限", "编辑权限", "/priv/edit", "/priv/edit", "sys:priv:dir:manage"},
        {"MENU", "sys:priv:url:view", "查看权限", "查看权限", "/priv/view", "/priv/view", "sys:priv:dir:manage"},
        {"DIR", "sys:role:dir:manage", "角色管理", "角色管理", "/role", "/role"},
        {"MENU", "sys:role:url:add", "新建角色", "新建角色", "/role/add", "/role/add", "sys:role:dir:manage"},
        {"MENU", "sys:role:url:edit", "编辑角色", "编辑角色", "/role/edit", "/role/edit", "sys:role:dir:manage"},
        {"MENU", "sys:role:url:view", "查看角色", "查看角色", "/role/view", "/role/view", "sys:role:dir:manage"},
        {"DIR", "sys:user:dir:manage", "用户管理", "用户管理", "/user", "/user"},
        {"MENU", "sys:user:url:add", "新建用户", "新建用户", "/user/add", "/user/add", "sys:user:dir:manage"},
        {"MENU", "sys:user:url:edit", "编辑用户", "编辑用户", "/user/edit", "/user/edit", "sys:user:dir:manage"},
        {"MENU", "sys:user:url:view", "查看用户", "查看用户", "/user/view", "/user/view", "sys:user:dir:manage"},
        {"DATA", "sys:priv:add", "新建权限", "新建权限", "", "", "sys:priv:dir:manage"},
        {"DATA", "sys:priv:delete", "删除权限", "删除权限", "", "", "sys:priv:dir:manage"},
        {"DATA", "sys:priv:edit", "编辑权限", "编辑权限", "", "", "sys:priv:dir:manage"},
        {"DATA", "sys:priv:view", "查看权限", "查看权限", "", "", "sys:priv:dir:manage"},
        {"DATA", "sys:role:add", "新建角色", "新建角色", "", "", "sys:role:dir:manage"},
        {"DATA", "sys:role:delete", "删除角色", "删除角色", "", "", "sys:role:dir:manage"},
        {"DATA", "sys:role:edit", "编辑角色", "编辑角色", "", "", "sys:role:dir:manage"},
        {"DATA", "sys:role:view", "查看角色", "查看角色", "", "", "sys:role:dir:manage"},
        {"DATA", "sys:role:allocate", "分配权限", "分配权限", "", "", "sys:role:dir:manage"},
        {"DATA", "sys:user:add", "新建用户", "新建用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:delete", "删除用户", "删除用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:edit", "编辑用户", "编辑用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:view", "查看用户", "查看用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:allocate", "分配角色", "分配角色", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:lock", "禁用用户", "禁用用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:unlock", "启用用户", "启用用户", "", "", "sys:user:dir:manage"},
        {"DATA", "sys:user:resetPwd", "重置密码", "重置密码", "", "", "sys:user:dir:manage"},
        {"MENU", "sys:profile:manage", "个人信息", "个人信息", "/profile/view", "/profile/view"},
        {"DATA", "sys:profile:changePwd", "修改密码", "修改密码", "", "", "sys:profile:manage"},

    };

    public final static String[][] ROLES = {
        {"SuperAdmin", "超级管理员", "超级管理员"},
        {"Admin", "管理员", "管理员"},
        {"User", "普通用户", "普通用户"},
    };
}
