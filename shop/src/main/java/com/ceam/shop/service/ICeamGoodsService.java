package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsDTO;
import com.ceam.mall.vo.CeamGoodsVO;
import com.ceam.shop.entity.CeamGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
public interface ICeamGoodsService extends IService<CeamGoods> {

    IPage<CeamGoodsVO> pageCeamGoodsVO(PageableDTO pageableDTO);

    void add(CeamGoodsDTO ceamGoodsDTO);

    IPage<CeamGoodsVO> queryNewOrHost(int page, int size, int nh);

    List<CeamGoodsVO> listByCateId(String categoryId);
}
