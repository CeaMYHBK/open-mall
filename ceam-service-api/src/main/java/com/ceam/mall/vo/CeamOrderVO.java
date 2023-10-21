package com.ceam.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CeaM
 * 2023/02/16 09:21
 **/
@Data
public class CeamOrderVO {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    /**
     * 确认时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime confirmTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deliveryTime;

    /**
     * 取消时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime cancelTime;

    /**
     * 收货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime collectTime;

    /**
     * 关闭时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime closeTime;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updTime;


    private String nickname;

    private String goodsName;

    private List<CeamOrderDetailVO> orderItems;

}
