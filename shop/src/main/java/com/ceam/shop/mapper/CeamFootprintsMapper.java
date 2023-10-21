package com.ceam.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ceam.mall.vo.CeamFootprintsVO;
import com.ceam.shop.entity.CeamFootprints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户浏览足迹表 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
public interface CeamFootprintsMapper extends BaseMapper<CeamFootprints> {

    IPage<CeamFootprintsVO> page(IPage iPage, @Param(Constants.WRAPPER) Wrapper wrapper);
}
