package com.ceam.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/16 09:22
 **/
@Data
public class CeamOrderDetailVO {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

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
     * 价格
     */
    private BigDecimal price;

    /**
     * 当前总价(单价*数量)
     */
    private BigDecimal totalPrice;

    /**
     * 规格
     */
    private String spec;

    /**
     * 购买数量
     */
    private Integer buyNumber;

    /**
     * 型号
     */
    private String model;

    /**
     * 重量（kg）
     */
    private BigDecimal specWeight;

    /**
     * 体积（m³）
     */
    private BigDecimal specVolume;

    /**
     * 编码
     */
    private String specCoding;

    /**
     * 条形码
     */
    private String specBarcode;

    /**
     * 退款金额
     */
    private BigDecimal refundPrice;

    /**
     * 退货数量
     */
    private Integer returnedQuantity;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

}
