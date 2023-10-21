package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/01/29 23:06
 **/
@Data
public class CeamGoodsCategoryDTO {

    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String pic;

    /**
     * 是否推荐
     */
    private Boolean isShow;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    private LocalDateTime updTime;

    /**
     * 删除状态
     */
    private Boolean deleted;

}
