package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsBargainDTO;
import com.ceam.mall.vo.CeamGoodsBargainVO;
import com.ceam.shop.service.ICeamGoodsBargainService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 砍价表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/shop/bargain")
public class CeamGoodsBargainController {

    private final ICeamGoodsBargainService goodsBargainService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamGoodsBargainVO> ceamGoodsBargainVOIPage = goodsBargainService.pageCeamGoodsBargainVO(pageableDTO);
        return ResponseEntity.ok(ceamGoodsBargainVOIPage);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CeamGoodsBargainDTO ceamGoodsBargainDTO) {
        goodsBargainService.add(ceamGoodsBargainDTO);
        return ResponseEntity.ok("");
    }
}
