package com.ceam.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.mall.dto.CeamGoodsCategoryDTO;
import com.ceam.mall.vo.CeamGoodsCategoryVO;
import com.ceam.shop.entity.CeamGoodsCategory;
import com.ceam.shop.mapper.CeamGoodsCategoryMapper;
import com.ceam.shop.service.ICeamGoodsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@Service
public class CeamGoodsCategoryServiceImpl extends ServiceImpl<CeamGoodsCategoryMapper, CeamGoodsCategory> implements ICeamGoodsCategoryService {

    @Override
    public List<CeamGoodsCategory> listAll(CeamGoodsCategoryDTO data) {
        LambdaQueryWrapper<CeamGoodsCategory> queryWrapper = Wrappers.<CeamGoodsCategory>lambdaQuery()
                .eq(CeamGoodsCategory::getDeleted, GlobalConstants.FALSE);
        List<CeamGoodsCategory> ceamGoodsCategories = baseMapper.selectList(queryWrapper);
        return ceamGoodsCategories;
    }

    @Override
    public Object buildTree(List<CeamGoodsCategory> categoryList) {
        List<CeamGoodsCategoryVO> categoryVOS = BeanCopyUtil
                .copyListProperties(categoryList, CeamGoodsCategoryVO.class);
        Set<CeamGoodsCategoryVO> trees = new LinkedHashSet<>();
        Set<CeamGoodsCategoryVO> cates = new LinkedHashSet<>();
        List<String> deptNames = categoryVOS.stream().map(CeamGoodsCategoryVO::getCateName)
                .collect(Collectors.toList());

        Boolean isChild;
        List<CeamGoodsCategory> categories = this.list();
        for (CeamGoodsCategoryVO categoryVO : categoryVOS) {
            isChild = false;
            if (GlobalConstants.STR_ZERO.equals(categoryVO.getPid().toString())) {
                trees.add(categoryVO);
            }
            for (CeamGoodsCategoryVO it : categoryVOS) {
                if (it.getPid().equals(categoryVO.getId())) {
                    isChild = true;
                    if (categoryVO.getChildren() == null) {
                        categoryVO.setChildren(new ArrayList<CeamGoodsCategoryVO>());
                    }
                    categoryVO.getChildren().add(it);
                }
            }
            if (isChild) {
                cates.add(categoryVO);
            }
            for (CeamGoodsCategory category : categories) {
                if (category.getId().equals(categoryVO.getPid())
                        && !deptNames.contains(category.getCateName())) {
                    cates.add(categoryVO);
                }
            }
        }


        if (CollectionUtils.isEmpty(trees)) {
            trees = cates;
        }


        Integer total = categoryVOS != null ? categoryVOS.size() : 0;

        Map map = new HashMap();
        map.put("total", total);
        map.put("records", CollectionUtils.isEmpty(trees) ? categoryVOS : trees);
        return map;
    }

    @Override
    public List<CeamGoodsCategoryVO> queryChannel() {
        LambdaQueryWrapper<CeamGoodsCategory> queryWrapper = Wrappers.<CeamGoodsCategory>lambdaQuery()
                .select(CeamGoodsCategory::getId, CeamGoodsCategory::getCateName, CeamGoodsCategory::getPic)
                .eq(CeamGoodsCategory::getPid, 0L)
                .eq(CeamGoodsCategory::getDeleted, GlobalConstants.FALSE);
        List<CeamGoodsCategory> ceamGoodsCategories = baseMapper.selectList(queryWrapper);

        return BeanCopyUtil.copyListProperties(ceamGoodsCategories, CeamGoodsCategoryVO.class);
    }
}
