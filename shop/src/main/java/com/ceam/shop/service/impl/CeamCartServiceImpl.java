package com.ceam.shop.service.impl;

import com.ceam.shop.entity.CeamCart;
import com.ceam.shop.mapper.CeamCartMapper;
import com.ceam.shop.service.ICeamCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Service
public class CeamCartServiceImpl extends ServiceImpl<CeamCartMapper, CeamCart> implements ICeamCartService {

}
