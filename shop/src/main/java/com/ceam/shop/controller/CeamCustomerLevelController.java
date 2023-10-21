package com.ceam.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.mall.dto.CeamCustomerLevelDTO;
import com.ceam.mall.vo.CeamCustomerLevelVO;
import com.ceam.shop.service.ICeamCustomerLevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 设置用户等级表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-02-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/shop/level")
public class CeamCustomerLevelController {

    private final ICeamCustomerLevelService customerLevelService;

    @GetMapping
    public ResponseEntity<Object> page(PageableDTO pageableDTO) {
        IPage<CeamCustomerLevelVO> ceamCustomerLevelVOIPage = customerLevelService
                .pageCeamCustomerLevelVO(pageableDTO);
        return ResponseEntity.ok(ceamCustomerLevelVOIPage);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CeamCustomerLevelDTO ceamCustomerLevelDTO) {
        customerLevelService.add(ceamCustomerLevelDTO);
        return ResponseEntity.ok("");
    }

}
