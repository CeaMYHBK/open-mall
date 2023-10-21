package com.ceam.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private String description;


}
