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
import com.ceam.mall.dto.CeamGoodsDTO;
import com.ceam.mall.vo.CeamGoodsVO;
import com.ceam.shop.entity.CeamCustomerLevel;
import com.ceam.shop.entity.CeamGoods;
import com.ceam.shop.mapper.CeamGoodsMapper;
import com.ceam.shop.service.ICeamGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@Service
public class CeamGoodsServiceImpl
        extends ServiceImpl<CeamGoodsMapper, CeamGoods> implements ICeamGoodsService {

    @Override
    public IPage<CeamGoodsVO> pageCeamGoodsVO(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamGoods> queryWrapper = Wrappers.<CeamGoods>lambdaQuery()
                .eq(CeamGoods::getDeleted, GlobalConstants.FALSE);
        Page<CeamGoods> pageEntity = new Page<>();

        Page<CeamGoods> ceamGoodsPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeamGoodsVO> pageVO = PageVOUtil
                .copyToPageVO(ceamGoodsPage, CeamGoodsVO.class);
        return pageVO;
    }

    @Override
    public void add(CeamGoodsDTO ceamGoodsDTO) {
        CeamGoods ceamCustomerLevel = BeanCopyUtil
                .copyProperties(ceamGoodsDTO, CeamGoods.class);

        ceamCustomerLevel.setAddTime(LocalDateTime.now());
        ceamCustomerLevel.setDeleted(GlobalConstants.FALSE);

        save(ceamCustomerLevel);
    }

    @Override
    public IPage<CeamGoodsVO> queryNewOrHost(int page, int size, int nh) {
        LambdaQueryWrapper<CeamGoods> queryWrapper = Wrappers.<CeamGoods>lambdaQuery()
                .eq(CeamGoods::getDeleted, GlobalConstants.FALSE)
                .orderByDesc(CeamGoods::getAddTime);
        if (nh == GlobalConstants.ZERO) {
            queryWrapper.eq(CeamGoods::getIsNew, GlobalConstants.TRUE);
        } else {
            queryWrapper.eq(CeamGoods::getIsHot, GlobalConstants.TRUE);
        }
        Page<CeamGoods> pageEntity = new Page<>();

        Page<CeamGoods> ceamGoodsPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeamGoodsVO> pageVO = PageVOUtil
                .copyToPageVO(ceamGoodsPage, CeamGoodsVO.class);
        return pageVO;
    }

    @Override
    public List<CeamGoodsVO> listByCateId(String categoryId) {
        LambdaQueryWrapper<CeamGoods> queryWrapper = Wrappers.<CeamGoods>lambdaQuery()
                .eq(CeamGoods::getCategoryId, Long.parseLong(categoryId))
                .orderByDesc(CeamGoods::getAddTime);
        List<CeamGoods> ceamGoods = baseMapper.selectList(queryWrapper);
        return BeanCopyUtil.copyListProperties(ceamGoods, CeamGoodsVO.class);
    }
}
