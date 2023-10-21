package com.ceam.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamFootprintsVO;
import com.ceam.shop.entity.CeamFootprints;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户浏览足迹表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
public interface ICeamFootprintsService extends IService<CeamFootprints> {

    IPage<CeamFootprintsVO> page(PageableDTO pageableDTO);
}
