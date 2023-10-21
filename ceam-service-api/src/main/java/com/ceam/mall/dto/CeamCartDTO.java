package com.ceam.mall.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/16 09:19
 **/
@Data
public class CeamCartDTO {

    private Long id;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String images;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 销售价格
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer buyCount;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;


}
