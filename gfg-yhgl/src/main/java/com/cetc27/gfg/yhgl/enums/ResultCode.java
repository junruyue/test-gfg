package com.cetc27.gfg.yhgl.enums;

public enum ResultCode {
    /*成功*/
    SUCCESS(200, "登录成功"),

    /*默认失败*/
    COMMEN_FAIL(999, "登录失败"),

    /*参数失败:1000~1999*/
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /*用户错误:2000~2999*/
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /*权限错误3000~3999*/
    NO_PERMISSION(3000, "没有权限");

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(int code) {

        for (ResultCode resultCode : values()) {
            if (resultCode.getCode() == code) {
                return resultCode.getMessage();
            }
        }

        return null;
    }
}
