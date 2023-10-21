package com.ceam.shop.service;

import com.ceam.mall.vo.CeamOrderDetailVO;
import com.ceam.shop.entity.CeamOrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单详情 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
public interface ICeamOrderDetailService extends IService<CeamOrderDetail> {

    List<CeamOrderDetailVO> listByOrderId(Long orderId);
}
