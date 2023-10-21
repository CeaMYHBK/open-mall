package com.ceam.mall.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/03 09:59
 **/
@Data
public class CeamGoodsDTO {

    private Long id;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 价格
     */
    private String price;

    /**
     * 市场价
     */
    private BigDecimal markingPrice;

    /**
     * 会员价
     */
    private BigDecimal memberPrice;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    private Boolean isHot;

    private String picUrl;

    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 1上架，2下架
     */
    private Integer status;

    private Boolean deleted;

    private LocalDateTime addTime;
}
