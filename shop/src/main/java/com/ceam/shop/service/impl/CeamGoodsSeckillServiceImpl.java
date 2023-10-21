package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.mall.dto.CeamGoodsSeckillDTO;
import com.ceam.mall.vo.CeamGoodsSeckillVO;
import com.ceam.mall.vo.CeamGoodsVO;
import com.ceam.shop.entity.CeamGoods;
import com.ceam.shop.entity.CeamGoodsSeckill;
import com.ceam.shop.mapper.CeamGoodsSeckillMapper;
import com.ceam.shop.service.ICeamGoodsSeckillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 商品秒杀产品表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@Service
public class CeamGoodsSeckillServiceImpl
        extends ServiceImpl<CeamGoodsSeckillMapper, CeamGoodsSeckill> implements ICeamGoodsSeckillService {

    @Override
    public IPage<CeamGoodsSeckillVO> pageCeamGoodsSeckillVO(PageableDTO pageableDTO) {
        LambdaQueryWrapper<CeamGoodsSeckill> queryWrapper = Wrappers.<CeamGoodsSeckill>lambdaQuery()
                .eq(CeamGoodsSeckill::getDeleted, GlobalConstants.FALSE);
        Page<CeamGoodsSeckill> pageEntity = new Page<>();

        Page<CeamGoodsSeckill> ceamGoodsSeckillPage = this.baseMapper.selectPage(pageEntity, queryWrapper);
        IPage<CeamGoodsSeckillVO> pageVO = PageVOUtil
                .copyToPageVO(ceamGoodsSeckillPage, CeamGoodsSeckillVO.class);
        return pageVO;
    }

    @Override
    public void add(CeamGoodsSeckillDTO ceamGoodsSeckillDTO) {
        CeamGoodsSeckill ceamGoodsSeckill = BeanCopyUtil.copyProperties(ceamGoodsSeckillDTO, CeamGoodsSeckill.class);
        ceamGoodsSeckill.setAddTime(LocalDateTime.now());
        ceamGoodsSeckill.setDeleted(GlobalConstants.FALSE);

        save(ceamGoodsSeckill);
    }

}
