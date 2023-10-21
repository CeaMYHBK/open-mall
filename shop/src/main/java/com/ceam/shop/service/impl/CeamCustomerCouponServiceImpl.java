package com.ceam.shop.service.impl;

import com.ceam.mall.vo.CeamCouponVO;
import com.ceam.mall.vo.CeamCustomerCouponVO;
import com.ceam.shop.entity.CeamCustomerCoupon;
import com.ceam.shop.mapper.CeamCustomerCouponMapper;
import com.ceam.shop.service.ICeamCustomerCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
@Service
public class CeamCustomerCouponServiceImpl extends ServiceImpl<CeamCustomerCouponMapper, CeamCustomerCoupon> implements ICeamCustomerCouponService {

    @Override
    public List<CeamCouponVO> listByCustomerId(Long customerId) {
        return baseMapper.listByCustomerId(customerId);
    }
}
