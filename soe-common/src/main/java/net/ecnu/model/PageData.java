package net.ecnu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author LYW
 * @since 2022-07-25
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageData {

    /**
     * 总条目数
     */
    private long total;

    /**
     * 每页条目数
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    /**
     * 列表数据
     */
    private Object records;

}
