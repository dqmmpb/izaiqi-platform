package com.izaiqi.platform.core.common;

public enum ErrorCode {

    // 系统异常
    ERROR_SYS_EXCEPTION(1001, "系统异常错误"),
    ERROR_INVALID_REQUEST(1002, "非法请求"),

    // 用户异常
    USER_NAME_HAS_EXIST(4001, "USER_NAME_HAS_EXIST"),
    USER_NOT_NULL(4002, "USER_NOT_NULL"),
    USER_NOT_EXIST(4003, "USER_NOT_EXIST"),
    USER_NAME_OR_PWD_ERROR(4004, "USER_NAME_OR_PWD_ERROR"),
    USER_PWD_ERROR(4005, "USER_PWD_ERROR"),
    USER_NOT_LOGIN(4006, "USER_NOT_LOGIN"),
    USER_HAS_LOCKED(4007, "USER_HAS_LOCKED"),
    USER_ORIGIN_PWD_ERROR(4008, "USER_ORIGIN_PWD_ERROR"),
    USER_PWD_LENGTH_ERROR(4009, "USER_PWD_LENGTH_ERROR"),
    USER_CHANGE_PWD_ORIGIN_AND_NEW_ARE_SAME_ERROR(4010, "USER_CHANGE_PWD_ORIGIN_AND_NEW_ARE_SAME_ERROR"),
    USER_BEGIN_END_DATE_ERROR(4011, "USER_BEGIN_END_DATE_ERROR"),
    USER_LOCK_SELF_ERROR(4012, "USER_LOCK_SELF_ERROR"),

    // 用户角色异常
    USERROLE_HAS_EXIST(4101, "USERROLE_HAS_EXIST"),
    ROLE_CODE_HAS_EXIST(4102, "ROLE_CODE_HAS_EXIST"),
    ROLE_HAS_USER_ROLE_REF(4103, "ROLE_HAS_USER_ROLE_REF"),
    ROLE_IS_NULL(4104, "ROLE_IS_NULL"),
    ROLE_NOT_EXIST(4105, "ROLE_NOT_EXIST"),
    // 角色权限异常
    ROLEPRIV_HAS_EXIST(4201, "ROLEPRIV_HAS_EXIST"),
    // 权限异常,
    PRIV_IS_NULL(4301, "PRIV_IS_NULL"),
    PRIV_CODE_HAS_EXIST(4302, "PRIV_CODE_HAS_EXIST"),
    PRIV_HAS_ROLE_PRIV_REF(4303, "PRIV_HAS_ROLE_PRIV_REF"),
    NO_PERMISSION(4304, "NO_PERMISSION"),;

    private int code;
    private String name;

    ErrorCode(int code, String name) {
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
