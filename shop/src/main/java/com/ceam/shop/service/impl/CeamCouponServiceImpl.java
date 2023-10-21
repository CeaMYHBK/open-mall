package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamCouponVO;
import com.ceam.shop.entity.CeamCoupon;
import com.ceam.shop.mapper.CeamCouponMapper;
import com.ceam.shop.service.ICeamCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券信息及规则表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
@Slf4j
@Service
@AllArgsConstructor
public class CeamCouponServiceImpl
        extends ServiceImpl<CeamCouponMapper, CeamCoupon> implements ICeamCouponService {

    @Override
    public IPage<CeamCouponVO> page(PageableDTO pageableDTO) {
        return null;
    }
}
