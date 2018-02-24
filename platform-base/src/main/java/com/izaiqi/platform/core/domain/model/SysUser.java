package com.izaiqi.platform.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_USER")
public class SysUser implements Serializable {
    @Id
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 密码盐值
     */
    @Column(name = "SALT")
    private String salt;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 手机号
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * A-可用/有效;X-不可用/无效
     */
    @Column(name = "STATE")
    private String state;

    /**
     * 状态变动时间
     */
    @Column(name = "STATE_TIME")
    private Date stateTime;

    /**
     * 是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定
     */
    @Column(name = "IS_LOCKED")
    private String isLocked;

    /**
     * 密码过期时间，NULL-永不过期
     */
    @Column(name = "PWD_EXP_TIME")
    private Date pwdExpTime;

    /**
     * 生效时间
     */
    @Column(name = "USER_EFF_TIME")
    private Date userEffTime;

    /**
     * 失效时间，NULL-永不失效
     */
    @Column(name = "USER_EXP_TIME")
    private Date userExpTime;

    /**
     * Y-允许强制登录，N-不允许
     */
    @Column(name = "FORCE_LOGIN")
    private String forceLogin;

    /**
     * 登录失败次数，空表示0
     */
    @Column(name = "LOGIN_FAIL")
    private Integer loginFail;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return USER_ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return USER_NAME - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码盐值
     *
     * @return SALT - 密码盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码盐值
     *
     * @param salt 密码盐值
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取姓名
     *
     * @return NAME - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取手机号
     *
     * @return MOBILE - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取A-可用/有效;X-不可用/无效
     *
     * @return STATE - A-可用/有效;X-不可用/无效
     */
    public String getState() {
        return state;
    }

    /**
     * 设置A-可用/有效;X-不可用/无效
     *
     * @param state A-可用/有效;X-不可用/无效
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取状态变动时间
     *
     * @return STATE_TIME - 状态变动时间
     */
    public Date getStateTime() {
        return stateTime;
    }

    /**
     * 设置状态变动时间
     *
     * @param stateTime 状态变动时间
     */
    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    /**
     * 获取是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定
     *
     * @return IS_LOCKED - 是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定
     *
     * @param isLocked 是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定
     */
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 获取密码过期时间，NULL-永不过期
     *
     * @return PWD_EXP_TIME - 密码过期时间，NULL-永不过期
     */
    public Date getPwdExpTime() {
        return pwdExpTime;
    }

    /**
     * 设置密码过期时间，NULL-永不过期
     *
     * @param pwdExpTime 密码过期时间，NULL-永不过期
     */
    public void setPwdExpTime(Date pwdExpTime) {
        this.pwdExpTime = pwdExpTime;
    }

    /**
     * 获取生效时间
     *
     * @return USER_EFF_TIME - 生效时间
     */
    public Date getUserEffTime() {
        return userEffTime;
    }

    /**
     * 设置生效时间
     *
     * @param userEffTime 生效时间
     */
    public void setUserEffTime(Date userEffTime) {
        this.userEffTime = userEffTime;
    }

    /**
     * 获取失效时间，NULL-永不失效
     *
     * @return USER_EXP_TIME - 失效时间，NULL-永不失效
     */
    public Date getUserExpTime() {
        return userExpTime;
    }

    /**
     * 设置失效时间，NULL-永不失效
     *
     * @param userExpTime 失效时间，NULL-永不失效
     */
    public void setUserExpTime(Date userExpTime) {
        this.userExpTime = userExpTime;
    }

    /**
     * 获取Y-允许强制登录，N-不允许
     *
     * @return FORCE_LOGIN - Y-允许强制登录，N-不允许
     */
    public String getForceLogin() {
        return forceLogin;
    }

    /**
     * 设置Y-允许强制登录，N-不允许
     *
     * @param forceLogin Y-允许强制登录，N-不允许
     */
    public void setForceLogin(String forceLogin) {
        this.forceLogin = forceLogin;
    }

    /**
     * 获取登录失败次数，空表示0
     *
     * @return LOGIN_FAIL - 登录失败次数，空表示0
     */
    public Integer getLoginFail() {
        return loginFail;
    }

    /**
     * 设置登录失败次数，空表示0
     *
     * @param loginFail 登录失败次数，空表示0
     */
    public void setLoginFail(Integer loginFail) {
        this.loginFail = loginFail;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
