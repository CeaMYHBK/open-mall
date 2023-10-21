package com.ceam.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysDictDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysDict;
import com.ceam.admin.service.ICeamSysDictService;
import com.ceam.admin.vo.CeamSysDictVO;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/dict")
public class CeamSysDictController {

    private final ICeamSysDictService ceamSysDictService;

    @GetMapping
    public R<IPage<CeamSysDictVO>> page(CeamSysDictDTO data) {
        IPage<CeamSysDictVO> ceamSysDictVOIPage = ceamSysDictService.pageCeamSysDictVO(data);
        return R.ok(ceamSysDictVOIPage);
    }

    @PostMapping
    public R<Object> add(@RequestBody CeamSysDictDTO data) {
        if (ObjectUtils.isNotEmpty(data.getId())) {
            throw new ServiceException("已存在");
        }
        ceamSysDictService.save(BeanCopyUtil.copyProperties(data, CeamSysDict.class));
        return R.ok("成功");
    }

    @PutMapping
    public R<Object> edit(@RequestBody CeamSysDictDTO data) {

        ceamSysDictService.saveOrUpdate(BeanCopyUtil.copyProperties(data, CeamSysDict.class));
        return R.ok("成功");
    }

    @DeleteMapping(value = "/{id}")
    public R<Object> add(@PathVariable Long id) {

        ceamSysDictService.removeById(id);
        return R.ok("成功");
    }
}
