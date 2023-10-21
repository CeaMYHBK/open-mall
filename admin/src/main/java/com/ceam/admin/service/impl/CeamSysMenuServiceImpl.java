package com.ceam.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.admin.dto.MenuDTO;
import com.ceam.admin.dto.RoleDTO;
import com.ceam.admin.entity.CeamSysMenu;
import com.ceam.admin.mapper.CeamSysMenuMapper;
import com.ceam.admin.service.ICeamSysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.admin.vo.MenuMetaVO;
import com.ceam.admin.vo.MenuVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@Service
public class CeamSysMenuServiceImpl
        extends ServiceImpl<CeamSysMenuMapper, CeamSysMenu> implements ICeamSysMenuService {

    @Override
    public List<MenuDTO> findByRoles(List<RoleDTO> roles, Integer type) {
        List<Long> roleIds = roles.stream().map(i -> {
            Long role = i.getId();
            return role;
        }).collect(Collectors.toList());

        List<CeamSysMenu> list = this.baseMapper.listByRoleIds(roleIds, type);
        if (CollectionUtils.isEmpty(list)) {
            throw new ServiceException("没有权限！");
        }
        List<MenuDTO> menuDTOS = BeanCopyUtil.copyListProperties(list, MenuDTO.class);
        return menuDTOS;
    }

    @Override
    public List<MenuDTO> findByRoleId(Long roleId, Integer type) {
        List<Long> roleIds = new ArrayList<>(1);
        roleIds.add(roleId);
        List<CeamSysMenu> list = this.baseMapper.listByRoleIds(roleIds, type);
        List<MenuDTO> menuDTOS = BeanCopyUtil.copyListProperties(list, MenuDTO.class);
        return menuDTOS;
    }

    @Override
    public List<CeamSysMenu> listAllMenu(MenuDTO data) {
        LambdaQueryWrapper<CeamSysMenu> queryWrapper = Wrappers.<CeamSysMenu>lambdaQuery()
                .eq(CeamSysMenu::getDeleted, GlobalConstants.FALSE);
        if (StringUtils.isNotBlank(data.getName())) {
            queryWrapper.like(CeamSysMenu::getName, data.getName());
        }
        List<CeamSysMenu> ceamSysMenus = baseMapper.selectList(queryWrapper);
        return ceamSysMenus;
    }

    @Override
    public List<CeamSysMenu> listMenuByType(MenuDTO data) {
        LambdaQueryWrapper<CeamSysMenu> queryWrapper = Wrappers.<CeamSysMenu>lambdaQuery()
                .eq(CeamSysMenu::getDeleted, GlobalConstants.FALSE)
                .eq(CeamSysMenu::getType, GlobalConstants.ONE)
                .orderByAsc(CeamSysMenu::getSort);
        if (StringUtils.isNotBlank(data.getName())) {
            queryWrapper.like(CeamSysMenu::getName, data.getName());
        }
        List<CeamSysMenu> ceamSysMenus = baseMapper.selectList(queryWrapper);
        return ceamSysMenus;
    }

    @Override
    public List<CeamSysMenu> findByPid(long pid) {
        LambdaQueryWrapper<CeamSysMenu> queryWrapper = Wrappers.<CeamSysMenu>lambdaQuery()
                .eq(CeamSysMenu::getPid, pid);

        List<CeamSysMenu> ceamSysMenus = baseMapper.selectList(queryWrapper);
        return ceamSysMenus;
    }

    @Override
    public void delete(Set<CeamSysMenu> menuSet) {
        for (CeamSysMenu menu : menuSet) {
            //roleMapper.untiedMenu(menu.getId());
            this.removeById(menu.getId());
        }
    }

    @Override
    public Set<CeamSysMenu> getDeleteMenus(List<CeamSysMenu> menuList,
                                           Set<CeamSysMenu> menuSet) {
        // 递归找出待删除的菜单
        for (CeamSysMenu menu1 : menuList) {
            menuSet.add(menu1);
            List<CeamSysMenu> menus = findByPid(menu1.getId());
            if (menus != null && menus.size() != 0) {
                getDeleteMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    @Override
    public Object getMenuTree(List<CeamSysMenu> menus) {
        List<Map<String, Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu != null) {
                        List<CeamSysMenu> ceamSysMenus = findByPid(menu.getId());
                        Map<String, Object> map = new HashMap<>(16);
                        map.put("id", menu.getId());
                        map.put("label", menu.getName());
                        if (ceamSysMenus != null && ceamSysMenus.size() != 0) {
                            map.put("children", getMenuTree(ceamSysMenus));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public Map<String, Object> buildTree(List<MenuDTO> menuDTOs) {
        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO menuDto : menuDTOs) {
            if (menuDto.getPid() == 0) {
                trees.add(menuDto);
            }
            for (MenuDTO it : menuDTOs) {
                if (it.getPid().equals(menuDto.getId())) {
                    if (menuDto.getChildren() == null) {
                        menuDto.setChildren(new ArrayList<>());
                    }
                    menuDto.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>(2);
        if (trees.size() == 0) {
            trees = menuDTOs.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("records", trees);
        map.put("total", menuDTOs.size());
        return map;
    }

    @Override
    public List<MenuVO> buildMenus(List<MenuDTO> menuDTOs) {
        List<MenuVO> list = new LinkedList<>();
        menuDTOs.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDtoList = menuDTO.getChildren();
                        MenuVO menuVo = new MenuVO();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getName());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == 0 ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == 0) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVO(menuDTO.getName(), menuDTO.getIcon(), !menuDTO.getCache()));
                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == 0) {
                            MenuVO menuVo1 = new MenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVO> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    @Override
    public void add(MenuDTO menuDTO) {
        CeamSysMenu ceamSysMenu = BeanCopyUtil.copyProperties(menuDTO, CeamSysMenu.class);
        ceamSysMenu.setAddTime(LocalDateTime.now());
        ceamSysMenu.setDeleted(GlobalConstants.FALSE);
        save(ceamSysMenu);
    }

    @Override
    public void edit(MenuDTO menuDTO) {
        CeamSysMenu ceamSysMenu = BeanCopyUtil.copyProperties(menuDTO, CeamSysMenu.class);
        updateById(ceamSysMenu);
    }

}
