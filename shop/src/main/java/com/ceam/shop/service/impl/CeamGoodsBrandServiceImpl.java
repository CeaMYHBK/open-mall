package com.ceam.shop.service.impl;

import com.ceam.shop.entity.CeamGoodsBrand;
import com.ceam.shop.mapper.CeamGoodsBrandMapper;
import com.ceam.shop.service.ICeamGoodsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌商表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-08
 */
@Slf4j
@Service
@AllArgsConstructor
public class CeamGoodsBrandServiceImpl
        extends ServiceImpl<CeamGoodsBrandMapper, CeamGoodsBrand> implements ICeamGoodsBrandService {

}
