package com.ceam.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ceam.admin.dto.CeamSysDeptDTO;
import com.ceam.admin.entity.CeamSysDept;
import com.ceam.admin.service.ICeamSysDeptService;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/dept")
public class CeamSysDeptController {

    private final ICeamSysDeptService ceamSysDeptService;

    @GetMapping
    public R<Object> getDeptTree(CeamSysDeptDTO data) {
        List<CeamSysDeptDTO> deptDTOs = BeanCopyUtil
                .copyListProperties(ceamSysDeptService.queryAll(data), CeamSysDeptDTO.class);
        return R.ok(ceamSysDeptService.buildTree(deptDTOs));
    }

    @GetMapping("list")
    public R<Object> deptTree(CeamSysDeptDTO data) {
        List<CeamSysDeptDTO> deptDTOs = BeanCopyUtil
                .copyListProperties(ceamSysDeptService.queryAll(data), CeamSysDeptDTO.class);
        Map tree = (Map)ceamSysDeptService.buildTree(deptDTOs);
        return R.ok(tree.get("records"));
    }

    @PostMapping
    public R<Object> add(@RequestBody CeamSysDept data) {
        data.setAddTime(LocalDateTime.now());
        data.setUpdTime(LocalDateTime.now());
        ceamSysDeptService.save(data);
        return R.ok("成功");
    }

    @PutMapping
    public R<Object> update(@RequestBody CeamSysDept data) {
        if (data.getId().equals(data.getPid())) {
            throw new ServiceException("上级不能为自己");
        }
        CeamSysDept dept = ceamSysDeptService.getOne(new LambdaQueryWrapper<CeamSysDept>()
                .eq(CeamSysDept::getId, data.getId()));

        if (ObjectUtils.isEmpty(dept)) {
            throw new ServiceException("不存在对应记录");
        }
        data.setId(dept.getId());
        data.setUpdTime(LocalDateTime.now());
        ceamSysDeptService.saveOrUpdate(data);
        return R.ok("成功");
    }

    @DeleteMapping
    public R<Object> delete(@RequestBody Set<Long> ids) {
        ceamSysDeptService.remove(ids);
        return R.ok();
    }
}
