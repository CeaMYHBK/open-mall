package com.ceam.admin.controller;


import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeaMSysRoleDTO;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.dto.RoleDTO;
import com.ceam.admin.entity.CeamSysRole;
import com.ceam.admin.service.ICeamSysRoleService;
import com.ceam.admin.service.ICeamSysUserService;
import com.ceam.admin.vo.CeaMSysRoleVO;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.R;
import com.ceam.common.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class CeamSysRoleController {

    private final ICeamSysRoleService ceamSysRoleService;
    private final ICeamSysUserService ceamSysUserService;

    @GetMapping
    public R<IPage<CeaMSysRoleVO>> getRoles(CeaMSysRoleDTO data) {
        IPage<CeaMSysRoleVO> mapIPage = ceamSysRoleService.pageCeaMSysRoleVO(data);
        return R.ok(mapIPage);
    }

    @GetMapping("all")
    public R<Object> getAll() {
        List<CeamSysRole> list = ceamSysRoleService.list();
        return R.ok(list);
    }

    @GetMapping(value = "/level")
    public ResponseEntity<Object> getLevel() {
        return new ResponseEntity<>(Dict.create().set("level", getLevels(null)), HttpStatus.OK);
    }

    @PutMapping(value = "/menu")
    public R<Object> updateMenu(@RequestBody CeaMSysRoleDTO roleDTO) {

        CeamSysRole role = ceamSysRoleService.getById(roleDTO.getId());
        getLevels(role.getLevel());
        ceamSysRoleService.updateMenu(roleDTO, role);
        return R.ok();
    }

    @PostMapping
    public R<Object> add(@RequestBody CeaMSysRoleDTO data) {
        boolean edit = ceamSysRoleService.add(data);
        return R.ok("成功");
    }

    @PutMapping
    public R<Object> edit(@RequestBody CeaMSysRoleDTO data) {
        boolean edit = ceamSysRoleService.edit(data);
        return R.ok("成功");
    }

    @DeleteMapping
    public R<Object> remove(@RequestBody Set<Long> ids) {
        ceamSysRoleService.remove(ids);
        return R.ok("成功");
    }

    /**
     * 获取用户的角色级别
     * @return /
     */
    private int getLevels(Integer level) {
        CeaMSysUserDTO user = ceamSysUserService.loadUserInfo(SecurityUtils.getUsername());
        List<RoleDTO> roleDTOS = ceamSysRoleService.listByUserId(user.getId());
        List<Integer> levels = roleDTOS.stream().map(RoleDTO::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if (level != null) {
            if (level < min) {
                throw new ServiceException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }
}
