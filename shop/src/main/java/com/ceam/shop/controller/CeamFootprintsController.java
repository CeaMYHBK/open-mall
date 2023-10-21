package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamFootprintsVO;
import com.ceam.shop.service.ICeamFootprintsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户浏览足迹表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/shop/footprints")
public class CeamFootprintsController {

    private final ICeamFootprintsService footprintsService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamFootprintsVO> page = footprintsService.page(pageableDTO);
        return ResponseEntity.ok(page);
    }
}
