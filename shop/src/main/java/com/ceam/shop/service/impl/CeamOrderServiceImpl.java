package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.vo.CeamCustomerVO;
import com.ceam.mall.vo.CeamOrderVO;
import com.ceam.shop.entity.CeamCustomer;
import com.ceam.shop.entity.CeamOrder;
import com.ceam.shop.mapper.CeamOrderMapper;
import com.ceam.shop.service.ICeamOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Service
public class CeamOrderServiceImpl
        extends ServiceImpl<CeamOrderMapper, CeamOrder> implements ICeamOrderService {

    @Override
    public IPage<CeamOrderVO> page(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamOrder> queryWrapper = Wrappers.<CeamOrder>lambdaQuery()
                .orderByDesc(CeamOrder::getAddTime);
        Page<CeamOrder> page = new Page<>();
        page.setCurrent((long)pageableDTO.getPage() + GlobalConstants.ONE);

        IPage<CeamOrderVO> pageVO = baseMapper.page(page, queryWrapper);

        return pageVO;
    }
}
