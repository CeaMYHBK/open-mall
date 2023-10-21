package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsBargainDTO;
import com.ceam.mall.vo.CeamGoodsBargainVO;
import com.ceam.shop.entity.CeamGoodsBargain;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 砍价表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
public interface ICeamGoodsBargainService extends IService<CeamGoodsBargain> {

    IPage<CeamGoodsBargainVO> pageCeamGoodsBargainVO(PageableDTO pageableDTO);

    void add(CeamGoodsBargainDTO ceamGoodsBargainDTO);
}
