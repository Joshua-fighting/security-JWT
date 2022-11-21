package net.ecnu.model.DTO;

import lombok.Data;

@Data
public class UpdateUserInfoDTO {
    /**
     * 用户账号id
     */
    private String accountNo;

    /**
     * 用户角色
     */
    private Integer roleId;
}
