package net.ecnu.model.DTO;

import lombok.Data;

@Data
public class GetUserInfoDTO {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 每页条目数
     */
    private long size;

    /**
     * 当前页
     */
    private long current;
}
