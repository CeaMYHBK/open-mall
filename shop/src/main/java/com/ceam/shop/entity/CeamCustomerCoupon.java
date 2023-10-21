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
 * 
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamCustomerCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
