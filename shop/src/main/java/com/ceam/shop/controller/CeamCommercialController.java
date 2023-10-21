package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.shop.service.ICeamCommercialService;
import com.ceam.mall.vo.CeaMCommercialVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 广告表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/commercial")
public class CeamCommercialController {

    private final ICeamCommercialService ceamCommercialService;

    @GetMapping
    public ResponseEntity<IPage<CeaMCommercialVO>> pageCeaMCommercialVO(@RequestParam Integer page,
                                                                        @RequestParam Integer size) {
        IPage<CeaMCommercialVO> ceaMCommercialVOIPage = ceamCommercialService.pageCeaMCommercialVO(page, size);
        return ResponseEntity.ok(ceaMCommercialVOIPage);
    }

}
