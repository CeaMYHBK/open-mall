package com.ceam.mall.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/03 20:03
 **/
@Data
public class CeamGoodsSeckillDTO {

    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 推荐图
     */
    private String picUrl;

    /**
     * 轮播图
     */
    private String images;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 简介
     */
    private String info;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 原价
     */
    private BigDecimal originPrice;

    /**
     * 返多少积分
     */
    private BigDecimal giveIntegral;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 单位名
     */
    private String unitName;

    /**
     * 内容
     */
    private String description;

    /**
     * 开始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    private LocalDateTime updTime;

    /**
     * 产品状态
     */
    private Integer status;

    /**
     * 删除 0未删除1已删除
     */
    private Boolean deleted;

    /**
     * 最多秒杀几个
     */
    private Integer maxNum;

    /**
     * 显示
     */
    private Boolean isShow;

    /**
     * 时间段id
     */
    private Integer timeId;

    /**
     * 规格 0单 1多
     */
    private Boolean specType;

    /**
     * 运费模板id
     */
    private Integer tempId;

}
