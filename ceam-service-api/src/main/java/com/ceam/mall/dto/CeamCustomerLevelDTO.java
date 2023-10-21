package com.ceam.mall.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/02 21:35
 **/
@Data
public class CeamCustomerLevelDTO {

    private Long id;

    /**
     * 会员名称
     */
    private String name;

    /**
     * 购买金额
     */
    private BigDecimal money;

    /**
     * 有效时间
     */
    private Integer validDate;

    /**
     * 是否为永久会员
     */
    private Boolean isForever;

    /**
     * 是否购买,1购买,0不购买
     */
    private Boolean isPay;

    /**
     * 是否显示 1显示,0隐藏
     */
    private Boolean isShow;

    /**
     * 会员等级
     */
    private Integer grade;

    /**
     * 享受折扣
     */
    private BigDecimal discount;

    /**
     * 会员卡背景
     */
    private String image;

    /**
     * 会员图标
     */
    private String icon;

    /**
     * 说明
     */
    private String remark;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    private LocalDateTime updTime;

    private Boolean deleted;
}
