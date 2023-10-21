package com.ceam.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.dto.MenuDTO;
import com.ceam.admin.dto.MenuMeta;
import com.ceam.admin.entity.CeamSysMenu;
import com.ceam.admin.service.ICeamSysMenuService;
import com.ceam.admin.service.ICeamSysRoleService;
import com.ceam.admin.service.ICeamSysUserService;
import com.ceam.admin.vo.MenuVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.R;
import com.ceam.common.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/menus")
public class CeamSysMenuController {

    private final ICeamSysMenuService menuService;
    private final ICeamSysUserService userInfoService;
    private final ICeamSysRoleService roleInfoService;

    @GetMapping(value = "/build")
    public R<Object> buildMenus() {
        CeaMSysUserDTO ceaMSysUserDTO = userInfoService.loadUserInfo(SecurityUtils.getUsername());
        List<MenuDTO> menuDtoList = menuService
                .findByRoles(roleInfoService.listByUserId(ceaMSysUserDTO.getId()), GlobalConstants.ONE);
        List<MenuDTO> menuDTOs = (List<MenuDTO>) menuService.buildTree(menuDtoList).get("records");
        return R.ok(menuService.buildMenus(menuDTOs));
    }

    @GetMapping
    public R<Object> listMenus(MenuDTO data) {

        List<CeamSysMenu> menuDtoList = menuService.listAllMenu(data);
        List<MenuDTO> menuDTOS = BeanCopyUtil.copyListProperties(menuDtoList, MenuDTO.class);
        Map<String, Object> map = menuService.buildTree(menuDTOS);
        return R.ok(map);
    }

    @GetMapping("list")
    public R<Object> menuList(MenuDTO data) {

        List<CeamSysMenu> menuDtoList = menuService.listAllMenu(data);
        List<MenuDTO> menuDTOS = BeanCopyUtil.copyListProperties(menuDtoList, MenuDTO.class);
        Map<String, Object> map = menuService.buildTree(menuDTOS);
        return R.ok(map.get("records"));
    }

    @GetMapping("listTypeOne")
    public R<Object> menuListTypeOne(MenuDTO data) {

        List<CeamSysMenu> menuDtoList = menuService.listMenuByType(data);
        List<MenuDTO> menuDTOS = BeanCopyUtil.copyListProperties(menuDtoList, MenuDTO.class);
        menuDTOS.forEach(item -> {
            MenuMeta meta = new MenuMeta();
            meta.setTitle(item.getName());
            meta.setIcon(item.getIcon());
            meta.setComponentName(item.getComponentName());
            item.setMeta(meta);
        });
        Map<String, Object> map = menuService.buildTree(menuDTOS);
        return R.ok(map.get("records"));
    }

    @GetMapping("tree")
    public R<Object> tree() {
        Object menuTree = menuService.getMenuTree(menuService.findByPid(0L));
        return R.ok(menuTree);
    }

    @PostMapping
    public R<Object> add(@RequestBody MenuDTO menuDTO) {
        menuService.add(menuDTO);
        return R.ok("");
    }

    @PutMapping
    public R<Object> edit(@RequestBody MenuDTO menuDTO) {
        menuService.edit(menuDTO);
        return R.ok("");
    }

    @DeleteMapping
    public R<Object> delete(@RequestBody Set<Long> ids) {

        Set<CeamSysMenu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<CeamSysMenu> menuList = menuService.findByPid(id);
            menuSet.add(menuService.getOne(new LambdaQueryWrapper<CeamSysMenu>()
                    .eq(CeamSysMenu::getId, id)));
            menuSet = menuService.getDeleteMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return R.ok();
    }
}
