package com.ceam.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysDictDetailDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysDictDetail;
import com.ceam.admin.service.ICeamSysDictDetailService;
import com.ceam.admin.vo.CeamSysDictDetailVO;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据字典详情 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/dictDetail")
public class CeamSysDictDetailController {

    private final ICeamSysDictDetailService ceamSysDictDetailService;

    @GetMapping
    public R<Object> page(CeamSysDictDetailDTO data) {
        IPage<CeamSysDictDetailVO> ceamSysDictDetailVOIPage = ceamSysDictDetailService
                .pageCeamSysDictDetailVO(data);
        return R.ok(ceamSysDictDetailVOIPage);
    }

    @PostMapping
    public R<Object> add(@RequestBody CeamSysDictDetailDTO data) {
        if (ObjectUtils.isNotEmpty(data.getId())) {
            throw new ServiceException("已存在");
        }
        data.setDictId(data.getDict().getId());
        ceamSysDictDetailService.save(BeanCopyUtil.copyProperties(data, CeamSysDictDetail.class));
        return R.ok("成功");
    }

    @PutMapping
    public R<Object> edit(@RequestBody CeamSysDictDetailDTO data) {

        ceamSysDictDetailService.saveOrUpdate(BeanCopyUtil.copyProperties(data, CeamSysDictDetail.class));
        return R.ok("成功");
    }

    @DeleteMapping(value = "/{id}")
    public R<Object> add(@PathVariable Long id) {

        ceamSysDictDetailService.removeById(id);
        return R.ok("成功");
    }
}
