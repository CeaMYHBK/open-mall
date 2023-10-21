package com.ceam.shop.mapper;

import com.ceam.mall.vo.CeamCouponVO;
import com.ceam.mall.vo.CeamCustomerCouponVO;
import com.ceam.shop.entity.CeamCustomerCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
public interface CeamCustomerCouponMapper extends BaseMapper<CeamCustomerCoupon> {

    List<CeamCouponVO> listByCustomerId(@Param("customerId") Long customerId);
}
