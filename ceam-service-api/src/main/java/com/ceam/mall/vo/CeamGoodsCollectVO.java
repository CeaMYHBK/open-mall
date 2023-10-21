package com.ceam.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/04 19:23
 **/
@Data
public class CeamGoodsCollectVO {

    private Long id;

    /**
     * 用户表的用户ID
     */
    private Long customerId;

    /**
     * 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    private Integer valueId;

    /**
     * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    private Integer type;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    private String nickname;

    private String goodsName;

}
