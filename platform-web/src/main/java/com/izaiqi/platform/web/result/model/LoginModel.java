package com.izaiqi.platform.web.result.model;

/**
 * 登录返回结果
 *
 * @author deng.qiming
 * @date 2017-01-09 10:15
 */
public class LoginModel {

    private boolean isLogin;
    private String userName;
    private String token;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
