package com.ceam.shop.service;

import com.ceam.mall.vo.CeamHomeMenuVO;
import com.ceam.shop.entity.CeamHomeMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-15
 */
public interface ICeamHomeMenuService extends IService<CeamHomeMenu> {

    List<CeamHomeMenuVO> listCeamHomeMenuVO();
}
