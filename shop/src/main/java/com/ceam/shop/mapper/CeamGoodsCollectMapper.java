package com.ceam.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ceam.mall.vo.CeamGoodsCollectVO;
import com.ceam.shop.entity.CeamGoodsCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
public interface CeamGoodsCollectMapper extends BaseMapper<CeamGoodsCollect> {

    IPage<CeamGoodsCollectVO> page(IPage iPage, @Param(Constants.WRAPPER) Wrapper wrapper);
}
