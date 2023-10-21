package com.ceam.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ceam.mall.vo.CeamOrderVO;
import com.ceam.shop.entity.CeamOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
public interface CeamOrderMapper extends BaseMapper<CeamOrder> {

    IPage<CeamOrderVO> page(IPage iPage, @Param(Constants.WRAPPER) Wrapper wrapper);
}
