package com.ceam.shop.service;

import com.ceam.mall.vo.CeamCouponVO;
import com.ceam.mall.vo.CeamCustomerCouponVO;
import com.ceam.shop.entity.CeamCustomerCoupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
public interface ICeamCustomerCouponService extends IService<CeamCustomerCoupon> {

    List<CeamCouponVO> listByCustomerId(Long customerId);

}
