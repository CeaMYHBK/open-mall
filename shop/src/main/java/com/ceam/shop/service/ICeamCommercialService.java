package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.shop.entity.CeamCommercial;
import com.ceam.mall.vo.CeaMCommercialVO;

/**
 * <p>
 * 广告表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-27
 */
public interface ICeamCommercialService extends IService<CeamCommercial> {

    IPage<CeaMCommercialVO> pageCeaMCommercialVO(Integer page, Integer size);
}
