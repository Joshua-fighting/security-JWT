package net.ecnu.model.VO;

import lombok.Data;

import java.util.Date;

@Data
public class GetUserInfoVO {
    /**
     * 用户账号id
     */
    private String accountNo;

    /**
     * 身份认证id
     */
    private String identifyId;

    /**
     * 用户角色ID
     */
    private Integer roleId;

    /**
     * 用户角色名称
     */
    private String roleStr;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户母语
     */
    private Integer firstLanguage;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
