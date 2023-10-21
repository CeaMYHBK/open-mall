package com.ceam.shop.service;

import com.ceam.mall.dto.CeamGoodsCategoryDTO;
import com.ceam.mall.vo.CeamGoodsCategoryVO;
import com.ceam.shop.entity.CeamGoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface ICeamGoodsCategoryService extends IService<CeamGoodsCategory> {

    List<CeamGoodsCategory> listAll(CeamGoodsCategoryDTO data);

    Object buildTree(List<CeamGoodsCategory> categoryDTOS);

    List<CeamGoodsCategoryVO> queryChannel();
}
