package net.ecnu.constant;


import java.util.regex.Pattern;

public class SOEConst {

    /**
     * 手机号正则表达式
     */
    public static final String PHONE_PATTERN = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

    /**
     * 邮箱正则表达式
     */
    public static final String MAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     *  通用参数-0
     */
    public static final Integer ZERO = 0;
}
