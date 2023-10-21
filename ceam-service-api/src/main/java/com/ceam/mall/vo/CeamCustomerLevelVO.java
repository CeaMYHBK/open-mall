package com.ceam.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/02 21:35
 **/
@Data
public class CeamCustomerLevelVO {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updTime;

    private Boolean deleted;
}
