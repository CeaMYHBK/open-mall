package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.mall.dto.CeamGoodsCategoryDTO;
import com.ceam.mall.vo.CeamGoodsCategoryVO;
import com.ceam.shop.entity.CeamGoodsCategory;
import com.ceam.shop.service.ICeamGoodsCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CeamGoodsCategoryController {

    private final ICeamGoodsCategoryService ceamGoodsCategoryService;

    @GetMapping
    public ResponseEntity<Object> getCeamCategoryTree(CeamGoodsCategoryDTO data) {

        List<CeamGoodsCategory> categoryDTOList = ceamGoodsCategoryService.listAll(data);
        return ResponseEntity.ok(ceamGoodsCategoryService.buildTree(categoryDTOList));
    }

    @GetMapping("channel")
    public ResponseEntity<Object> get() {
        List<CeamGoodsCategoryVO> ceamGoodsCategoryVOS = ceamGoodsCategoryService.queryChannel();
        return ResponseEntity.ok(ceamGoodsCategoryVOS);
    }
}
