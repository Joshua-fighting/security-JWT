package net.ecnu.enums;

import lombok.Getter;

public enum BizCodeEnum {

    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001, "账号已经存在"),
    ACCOUNT_UNREGISTER(250002, "账号不存在"),
    ACCOUNT_PWD_ERROR(250003, "账号或者密码错误"),
    ACCOUNT_UNLOGIN(250004, "账号未登录"),

    /**
     * 传参异常
     */
    PARAM_IS_EMPTY(300000,"参数为空"),
    TOEKN_EXCEPTION(310000,"用户登录信息异常或已失效"),

    /**
     * 用户权限
     */
    USER_INPUT_ERROR(400,"您输入的数据格式错误或您没有权限访问资源！"),
    USER_ALREADY_EXISTS(401,"用户已存在")
    ;
    @Getter
    private final int code;

    @Getter
    private final String message;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}