package com.ceam.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysJobDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.service.ICeamSysJobService;
import com.ceam.admin.vo.CeamSysJobVO;
import com.ceam.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/job")
public class CeamSysJobController {

    private final ICeamSysJobService ceamSysJobService;

    @GetMapping
    public R<IPage<CeamSysJobVO>> page(CeamSysJobDTO data) {
        IPage<CeamSysJobVO> ceamSysJobVOIPage = ceamSysJobService.pageCeamSysJobVO(data);
        return R.ok(ceamSysJobVOIPage);
    }

    @PostMapping
    public R<Object> add(@RequestBody CeamSysJobDTO data) {
        boolean add = ceamSysJobService.add(data);
        return R.ok(add);
    }

    @PutMapping
    public ResponseEntity<Object> edit(@RequestBody CeamSysJobDTO data) {
        boolean add = ceamSysJobService.edit(data);
        return ResponseEntity.ok(add);
    }

    @DeleteMapping
    public R<Object> remove(@RequestBody Set<Long> ids) {
        ceamSysJobService.remove(ids);
        return R.ok("成功");
    }
}
