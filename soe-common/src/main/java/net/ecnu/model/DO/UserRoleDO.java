package net.ecnu.model.DO;

import lombok.Data;

@Data
public class UserRoleDO {
    /**
     *
     */
    private Integer id;

    /**
     *  用户账号id
     */
    private String accountNo;

    /**
     *  用户角色
     */
    private String roleId;
}

