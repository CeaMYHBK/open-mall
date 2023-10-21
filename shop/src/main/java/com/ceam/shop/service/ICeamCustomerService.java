package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamCustomerVO;
import com.ceam.shop.entity.CeamCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
public interface ICeamCustomerService extends IService<CeamCustomer> {

    IPage<CeamCustomerVO> page(PageableDTO pageableDTO);
}
