package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.vo.CeamFootprintsVO;
import com.ceam.mall.vo.CeamGoodsCollectVO;
import com.ceam.shop.entity.CeamFootprints;
import com.ceam.shop.entity.CeamGoodsCollect;
import com.ceam.shop.mapper.CeamGoodsCollectMapper;
import com.ceam.shop.service.ICeamGoodsCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
@Service
public class CeamGoodsCollectServiceImpl extends ServiceImpl<CeamGoodsCollectMapper, CeamGoodsCollect> implements ICeamGoodsCollectService {

    @Override
    public IPage<CeamGoodsCollectVO> page(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamGoodsCollect> queryWrapper = Wrappers.<CeamGoodsCollect>lambdaQuery()
                .eq(CeamGoodsCollect::getDeleted, GlobalConstants.FALSE);
        Page<CeamGoodsCollect> pageEntity = new Page<>();
        pageEntity.setCurrent((long)pageableDTO.getPage() + GlobalConstants.ONE);
        IPage<CeamGoodsCollectVO> page = baseMapper.page(pageEntity, queryWrapper);
        return page;
    }
}
