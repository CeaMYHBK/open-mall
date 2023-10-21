package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.vo.CeaMCommercialVO;
import com.ceam.mall.vo.CeamFootprintsVO;
import com.ceam.shop.entity.CeamCommercial;
import com.ceam.shop.entity.CeamFootprints;
import com.ceam.shop.mapper.CeamFootprintsMapper;
import com.ceam.shop.service.ICeamFootprintsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户浏览足迹表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
@Service
public class CeamFootprintsServiceImpl extends ServiceImpl<CeamFootprintsMapper, CeamFootprints> implements ICeamFootprintsService {

    @Override
    public IPage<CeamFootprintsVO> page(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamFootprints> queryWrapper = Wrappers.<CeamFootprints>lambdaQuery()
                .eq(CeamFootprints::getDeleted, GlobalConstants.FALSE);
        Page<CeamFootprints> pageEntity = new Page<>();
        pageEntity.setCurrent((long)pageableDTO.getPage() + GlobalConstants.ONE);
        pageEntity.setSize((long)pageableDTO.getSize());
        IPage<CeamFootprintsVO> page = baseMapper.page(pageEntity, queryWrapper);
        return page;
    }
}
