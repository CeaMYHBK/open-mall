package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamGoodsCollectVO;
import com.ceam.shop.service.ICeamGoodsCollectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/shop/collect")
public class CeamGoodsCollectController {

    private final ICeamGoodsCollectService goodsCollectService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamGoodsCollectVO> page = goodsCollectService.page(pageableDTO);
        return ResponseEntity.ok(page);
    }
}
