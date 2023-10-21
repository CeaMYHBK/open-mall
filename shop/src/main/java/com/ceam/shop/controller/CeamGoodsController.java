package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsDTO;
import com.ceam.mall.vo.CeamGoodsVO;
import com.ceam.shop.service.ICeamGoodsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/goods")
public class CeamGoodsController {

    private final ICeamGoodsService goodsService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamGoodsVO> ceamGoodsVOIPage = goodsService.pageCeamGoodsVO(pageableDTO);
        return ResponseEntity.ok(ceamGoodsVOIPage);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CeamGoodsDTO ceamGoodsDTO) {
        goodsService.add(ceamGoodsDTO);
        return ResponseEntity.ok("");
    }

    @GetMapping("categoryId")
    public ResponseEntity<Object> listByCateId(@RequestParam String categoryId) {
        List<CeamGoodsVO> ceamGoodsVOS = goodsService.listByCateId(categoryId);
        return ResponseEntity.ok(ceamGoodsVOS);
    }
}
