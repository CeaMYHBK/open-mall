package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/04 19:24
 **/
@Data
public class CeamGoodsCollectDTO {

    private Long id;

    /**
     * 用户表的用户ID
     */
    private Long customerId;

    /**
     * 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    private Integer valueId;

    /**
     * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    private Integer type;

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
