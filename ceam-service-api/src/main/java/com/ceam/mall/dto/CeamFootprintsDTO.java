package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/04 19:22
 **/
@Data
public class CeamFootprintsDTO {

    private Long id;

    /**
     * 用户表的用户ID
     */
    private Long customerId;

    /**
     * 浏览商品ID
     */
    private Long goodsId;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

}
