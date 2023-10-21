package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamOrderVO;
import com.ceam.shop.entity.CeamOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
public interface ICeamOrderService extends IService<CeamOrder> {

    IPage<CeamOrderVO> page(PageableDTO pageableDTO);
}
