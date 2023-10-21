package com.ceam.mall.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/16 09:20
 **/
@Data
public class CeamGoodsCommentsVO {

    private Long id;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 业务订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 业务类型名称（如订单 order）
     */
    private String businessType;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 图片数据（一维数组json）
     */
    private String images;

    /**
     * 回复内容
     */
    private String reply;

    /**
     * 评价级别（默认0 1~5）
     */
    private Boolean rating;

    /**
     * 是否显示（0否, 1是）
     */
    private Boolean isShow;

    /**
     * 是否匿名（0否，1是）
     */
    private Boolean isAnonymous;

    /**
     * 是否回复（0否，1是）
     */
    private Boolean isReply;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

}
