package com.ceam.admin.service;

import com.ceam.admin.dto.MenuDTO;
import com.ceam.admin.dto.RoleDTO;
import com.ceam.admin.entity.CeamSysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.admin.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
public interface ICeamSysMenuService extends IService<CeamSysMenu> {

    List<MenuDTO> findByRoles(List<RoleDTO> roles, Integer type);

    List<MenuDTO> findByRoleId(Long roleId, Integer type);

    List<CeamSysMenu> listAllMenu(MenuDTO data);

    List<CeamSysMenu> listMenuByType(MenuDTO data);

    /**
     * 根据pid查询
     * @param pid /
     * @return /
     */
    List<CeamSysMenu> findByPid(long pid);

    /**
     * 删除
     * @param menuSet /
     */
    void delete(Set<CeamSysMenu> menuSet);

    /**
     * 获取待删除的菜单
     * @param menuList /
     * @param menuSet /
     * @return /
     */
    Set<CeamSysMenu> getDeleteMenus(List<CeamSysMenu> menuList,
                                    Set<CeamSysMenu> menuSet);

    Object getMenuTree(List<CeamSysMenu> menus);

    /**
     * 构建菜单树
     * @param menuDTOs 原始数据
     * @return /
     */
    Map<String, Object> buildTree(List<MenuDTO> menuDTOs);

    /**
     * 构建菜单树
     * @param menuDTOs /
     * @return /
     */
    List<MenuVO> buildMenus(List<MenuDTO> menuDTOs);

    void add(MenuDTO menuDTO);

    void edit(MenuDTO menuDTO);
}
