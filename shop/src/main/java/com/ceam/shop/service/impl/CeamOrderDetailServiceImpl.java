package com.ceam.shop.service.impl;

import com.ceam.mall.vo.CeamOrderDetailVO;
import com.ceam.shop.entity.CeamOrderDetail;
import com.ceam.shop.mapper.CeamOrderDetailMapper;
import com.ceam.shop.service.ICeamOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单详情 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Service
public class CeamOrderDetailServiceImpl
        extends ServiceImpl<CeamOrderDetailMapper, CeamOrderDetail> implements ICeamOrderDetailService {

    @Override
    public List<CeamOrderDetailVO> listByOrderId(Long orderId) {

        return baseMapper.listByOrderId(orderId);
    }
}
