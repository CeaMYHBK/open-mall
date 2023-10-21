package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamCustomerLevelDTO;
import com.ceam.mall.vo.CeamCustomerLevelVO;
import com.ceam.shop.entity.CeamCustomerLevel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设置用户等级表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-02
 */
public interface ICeamCustomerLevelService extends IService<CeamCustomerLevel> {

    IPage<CeamCustomerLevelVO> pageCeamCustomerLevelVO(PageableDTO pageableDTO);

    void add(CeamCustomerLevelDTO ceamCustomerLevelDTO);
}
