package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamCouponVO;
import com.ceam.shop.entity.CeamCoupon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券信息及规则表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
public interface ICeamCouponService extends IService<CeamCoupon> {

    IPage<CeamCouponVO> page(PageableDTO pageableDTO);
}
