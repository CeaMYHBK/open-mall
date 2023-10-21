package com.ceam.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品评论
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamGoodsComments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
