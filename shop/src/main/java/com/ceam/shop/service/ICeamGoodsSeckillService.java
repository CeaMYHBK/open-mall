package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsSeckillDTO;
import com.ceam.mall.vo.CeamGoodsSeckillVO;
import com.ceam.shop.entity.CeamGoodsSeckill;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品秒杀产品表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
public interface ICeamGoodsSeckillService extends IService<CeamGoodsSeckill> {

    IPage<CeamGoodsSeckillVO> pageCeamGoodsSeckillVO(PageableDTO pageableDTO);

    void add(CeamGoodsSeckillDTO ceamGoodsSeckillDTO);
}
