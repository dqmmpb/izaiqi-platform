package com.izaiqi.platform.web.common;

public enum Error {

    I18N_MESSAGE_TEST(101, "测试国际化"),
    I18N_MESSAGE_TEST_PARAMS(102, "测试国际化-参数化"),

    // 业务异常（5位异常代码）
    ERROR_SYS_EXCEPTION(10001, "系统异常错误"),
    ERROR_INVALID_REQUEST(10002, "系统异常错误"),
    ERROR_PARAMS(10003, "参数错误"),
    ERROR_PARAMS_SIGN_FAILED(10004, "参数签名错误"),

    // 用户异常
    USER_SESSION_TIMEOUT(14001, "USER_SESSION_TIMEOUT"),;

    private int code;
    private String name;

    Error(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getCodeString() {
        return "" + code;
    }
}
