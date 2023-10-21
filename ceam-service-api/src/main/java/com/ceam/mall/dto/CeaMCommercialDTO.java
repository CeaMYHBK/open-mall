package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/01/28 10:03
 **/
@Data
public class CeaMCommercialDTO {

    /**
     * 广告标题
     */
    private String title;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    private String link;

    /**
     * 广告宣传图片
     */
    private String url;

    /**
     * 广告位置：1则是首页
     */
    private Integer position;

    /**
     * 活动内容
     */
    private String description;

    /**
     * 广告开始时间
     */
    private LocalDateTime startTime;

    /**
     * 广告结束时间
     */
    private LocalDateTime endTime;

    private Boolean deleted;

    private LocalDateTime addTime;

    private Long addBy;

    private LocalDateTime updTime;

    private Long updBy;

    /**
     * 1启用，0禁用
     */
    private Integer status;
}
