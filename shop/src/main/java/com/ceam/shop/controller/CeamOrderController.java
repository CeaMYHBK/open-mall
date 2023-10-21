package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamOrderVO;
import com.ceam.shop.service.ICeamOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class CeamOrderController {

    private final ICeamOrderService orderService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamOrderVO> page = orderService.page(pageableDTO);
        return ResponseEntity.ok(page);
    }
}
