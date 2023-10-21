package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.shop.entity.CeamCommercial;
import com.ceam.shop.mapper.CeamCommercialMapper;
import com.ceam.shop.service.ICeamCommercialService;
import com.ceam.mall.vo.CeaMCommercialVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-27
 */
@Service
public class CeamCommercialServiceImpl extends ServiceImpl<CeamCommercialMapper, CeamCommercial> implements ICeamCommercialService {

    @Override
    public IPage<CeaMCommercialVO> pageCeaMCommercialVO(Integer page, Integer size) {
        LambdaQueryWrapper<CeamCommercial> queryWrapper = Wrappers.<CeamCommercial>lambdaQuery()
                .eq(CeamCommercial::getStatus, GlobalConstants.ONE);
        Page<CeamCommercial> pageEntity = new Page<>(page, size);
        Page<CeamCommercial> ceamCommercialPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeaMCommercialVO> pageVO = PageVOUtil.copyToPageVO(ceamCommercialPage, CeaMCommercialVO.class);
        return pageVO;
    }

}
