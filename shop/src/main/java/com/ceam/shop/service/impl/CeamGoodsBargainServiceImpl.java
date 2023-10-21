package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.dto.CeamGoodsBargainDTO;
import com.ceam.mall.vo.CeamGoodsBargainVO;
import com.ceam.mall.vo.CeamGoodsSeckillVO;
import com.ceam.shop.entity.CeamGoodsBargain;
import com.ceam.shop.entity.CeamGoodsSeckill;
import com.ceam.shop.mapper.CeamGoodsBargainMapper;
import com.ceam.shop.service.ICeamGoodsBargainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 砍价表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@Service
public class CeamGoodsBargainServiceImpl
        extends ServiceImpl<CeamGoodsBargainMapper, CeamGoodsBargain> implements ICeamGoodsBargainService {

    @Override
    public IPage<CeamGoodsBargainVO> pageCeamGoodsBargainVO(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamGoodsBargain> queryWrapper = Wrappers.<CeamGoodsBargain>lambdaQuery()
                .eq(CeamGoodsBargain::getDeleted, GlobalConstants.FALSE);
        Page<CeamGoodsBargain> pageEntity = new Page<>();

        Page<CeamGoodsBargain> ceamGoodsBargainPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeamGoodsBargainVO> pageVO = PageVOUtil
                .copyToPageVO(ceamGoodsBargainPage, CeamGoodsBargainVO.class);
        return pageVO;
    }

    @Override
    public void add(CeamGoodsBargainDTO ceamGoodsBargainDTO) {
        CeamGoodsBargain ceamGoodsBargain = BeanCopyUtil.copyProperties(ceamGoodsBargainDTO, CeamGoodsBargain.class);
        ceamGoodsBargain.setAddTime(LocalDateTime.now());
        ceamGoodsBargain.setDeleted(GlobalConstants.FALSE);
    }
}
