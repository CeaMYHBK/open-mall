package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/08 18:52
 **/
@Data
public class CeamCustomerCouponDTO {

    private Long id;

    private Long customerId;

    private Long couponId;

    private Integer status;

    private LocalDateTime usedTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String orderSn;

    private LocalDateTime addTime;

    private LocalDateTime updTime;

    private Boolean deleted;

}
