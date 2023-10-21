package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamGoodsSeckillDTO;
import com.ceam.mall.vo.CeamGoodsSeckillVO;
import com.ceam.shop.service.ICeamGoodsSeckillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品秒杀产品表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/seckill")
public class CeamGoodsSeckillController {

    private final ICeamGoodsSeckillService goodsSeckillService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamGoodsSeckillVO> ceamGoodsSeckillVOIPage = goodsSeckillService.pageCeamGoodsSeckillVO(pageableDTO);
        return ResponseEntity.ok(ceamGoodsSeckillVOIPage);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CeamGoodsSeckillDTO ceamGoodsSeckillDTO) {
        goodsSeckillService.add(ceamGoodsSeckillDTO);
        return ResponseEntity.ok("");
    }
}
