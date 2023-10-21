package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.dto.CeamCustomerLevelDTO;
import com.ceam.mall.vo.CeaMCommercialVO;
import com.ceam.mall.vo.CeamCustomerLevelVO;
import com.ceam.shop.entity.CeamCommercial;
import com.ceam.shop.entity.CeamCustomerLevel;
import com.ceam.shop.mapper.CeamCustomerLevelMapper;
import com.ceam.shop.service.ICeamCustomerLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 设置用户等级表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-02
 */
@Service
public class CeamCustomerLevelServiceImpl extends
        ServiceImpl<CeamCustomerLevelMapper, CeamCustomerLevel> implements ICeamCustomerLevelService {

    @Override
    public IPage<CeamCustomerLevelVO> pageCeamCustomerLevelVO(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamCustomerLevel> queryWrapper = Wrappers.<CeamCustomerLevel>lambdaQuery()
                .eq(CeamCustomerLevel::getDeleted, GlobalConstants.FALSE);
        Page<CeamCustomerLevel> pageEntity = new Page<>();

        Page<CeamCustomerLevel> ceamCustomerLevelPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeamCustomerLevelVO> pageVO = PageVOUtil
                .copyToPageVO(ceamCustomerLevelPage, CeamCustomerLevelVO.class);
        return pageVO;
    }

    @Override
    public void add(CeamCustomerLevelDTO ceamCustomerLevelDTO) {
        CeamCustomerLevel ceamCustomerLevel = BeanCopyUtil
                .copyProperties(ceamCustomerLevelDTO, CeamCustomerLevel.class);

        ceamCustomerLevel.setAddTime(LocalDateTime.now());
        ceamCustomerLevel.setDeleted(GlobalConstants.FALSE);
        ceamCustomerLevel.setUpdTime(LocalDateTime.now());

        save(ceamCustomerLevel);
    }
}
