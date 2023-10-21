package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamGoodsCollectVO;
import com.ceam.shop.entity.CeamGoodsCollect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
public interface ICeamGoodsCollectService extends IService<CeamGoodsCollect> {

    IPage<CeamGoodsCollectVO> page(PageableDTO pageableDTO);
}
