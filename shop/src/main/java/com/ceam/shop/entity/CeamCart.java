package com.ceam.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
