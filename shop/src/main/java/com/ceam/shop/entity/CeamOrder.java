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
 * 订单
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 快递id
     */
    private Long expressId;

    /**
     * 快递单号
     */
    private String expressNumber;

    /**
     * 支付方式id
     */
    private Long paymentId;

    /**
     * 订单状态（0待确认, 1已确认/待支付, 2已支付/待发货, 3已发货/待收货, 4已完成, 5已取消, 6已关闭）
     */
    private Integer status;

    /**
     * 支付状态（0未支付, 1已支付, 2已退款, 3部分退款）
     */
    private Integer payStatus;

    /**
     * 扩展展示数据
     */
    private String extensionData;

    /**
     * 购买商品总数量
     */
    private Integer buyNumberCount;

    /**
     * 增加的金额
     */
    private BigDecimal increasePrice;

    /**
     * 优惠金额
     */
    private BigDecimal preferentialPrice;

    /**
     * 订单单价
     */
    private BigDecimal price;

    /**
     * 订单总价(订单最终价格)
     */
    private BigDecimal totalPrice;

    /**
     * 已支付金额
     */
    private BigDecimal payPrice;

    /**
     * 退款金额
     */
    private BigDecimal refundPrice;

    /**
     * 退货数量
     */
    private Integer returnedQuantity;

    /**
     * 客户端类型（pc, h5, ios, android, alipay, weixin, baidu）取APPLICATION_CLIENT_TYPE常量值
     */
    private String clientType;

    /**
     * 订单模式（0销售型, 1展示型, 2自提点, 3虚拟销售）
     */
    private Integer orderModel;

    /**
     * 是否线下支付（0否，1是）
     */
    private Integer isUnderLine;

    /**
     * 用户备注
     */
    private String userNote;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 收货时间
     */
    private LocalDateTime collectTime;

    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 评论时间
     */
    private LocalDateTime commentsTime;

    /**
     * 是否已评论（0否, 大于0评论时间）
     */
    private LocalDateTime isComments;

    /**
     * 用户是否已评论（0否, 大于0评论时间）
     */
    private LocalDateTime userIsComments;

    /**
     * 是否已删除（0否, 大于0删除时间）
     */
    private LocalDateTime isDeleteTime;

    /**
     * 用户是否已删除（0否, 大于0删除时间）
     */
    private LocalDateTime userIsDeleteTime;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;


}
