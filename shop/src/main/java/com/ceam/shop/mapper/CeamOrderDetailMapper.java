package com.ceam.shop.mapper;

import com.ceam.mall.vo.CeamOrderDetailVO;
import com.ceam.shop.entity.CeamOrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单详情 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
public interface CeamOrderDetailMapper extends BaseMapper<CeamOrderDetail> {

    List<CeamOrderDetailVO> listByOrderId(@Param("orderId") Long orderId);
}
