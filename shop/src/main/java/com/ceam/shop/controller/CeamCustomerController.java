package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.vo.CeamCustomerVO;
import com.ceam.shop.service.ICeamCustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CeamCustomerController {

    private final ICeamCustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamCustomerVO> page = customerService.page(pageableDTO);
        return ResponseEntity.ok(page);
    }
}
