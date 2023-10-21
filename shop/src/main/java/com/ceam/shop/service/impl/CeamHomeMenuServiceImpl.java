package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.mall.vo.CeamHomeMenuVO;
import com.ceam.shop.entity.CeamHomeMenu;
import com.ceam.shop.mapper.CeamHomeMenuMapper;
import com.ceam.shop.service.ICeamHomeMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-02-15
 */
@Service
public class CeamHomeMenuServiceImpl extends ServiceImpl<CeamHomeMenuMapper, CeamHomeMenu> implements ICeamHomeMenuService {

    @Override
    public List<CeamHomeMenuVO> listCeamHomeMenuVO() {
        LambdaQueryWrapper<CeamHomeMenu> queryWrapper = Wrappers.<CeamHomeMenu>lambdaQuery()
                .eq(CeamHomeMenu::getDeleted, GlobalConstants.FALSE)
                .eq(CeamHomeMenu::getIsShow, GlobalConstants.TRUE);
        List<CeamHomeMenu> ceamHomeMenus = baseMapper.selectList(queryWrapper);

        return BeanCopyUtil.copyListProperties(ceamHomeMenus, CeamHomeMenuVO.class);
    }
}
