package com.ceam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.CeaMSysRoleDTO;
import com.ceam.admin.dto.MenuDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.dto.RoleDTO;
import com.ceam.admin.entity.CeamRoleMenu;
import com.ceam.admin.entity.CeamSysRole;
import com.ceam.admin.mapper.CeamSysRoleMapper;
import com.ceam.admin.service.ICeamRoleMenuService;
import com.ceam.admin.service.ICeamSysMenuService;
import com.ceam.admin.service.ICeamSysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.admin.vo.CeaMSysRoleVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.PageVOUtil;
import com.ceam.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@Slf4j
@Service
@AllArgsConstructor
public class CeamSysRoleServiceImpl
        extends ServiceImpl<CeamSysRoleMapper, CeamSysRole> implements ICeamSysRoleService {

    private ICeamSysMenuService ceamSysMenuService;
    private ICeamRoleMenuService ceamRoleMenuService;

    @Override
    public IPage<CeaMSysRoleVO> pageCeaMSysRoleVO(CeaMSysRoleDTO data) {
        log.info("请求入参{}", data);
        LambdaQueryWrapper<CeamSysRole> queryWrapper = Wrappers.<CeamSysRole>lambdaQuery()
                .eq(CeamSysRole::getDeleted, GlobalConstants.FALSE);

        if (StringUtils.isNotBlank(data.getName())) {
            queryWrapper.like(CeamSysRole::getName, data.getName());
        }
        if (ObjectUtils.isNotEmpty(data.getEnabled())) {
            queryWrapper.like(CeamSysRole::getEnabled, data.getEnabled());
        }
        Page<CeamSysRole> page = new Page<>(data.getPage(), data.getSize());
        // 手动设置当前页，不然分页失效
        page.setCurrent((long)data.getPage() - GlobalConstants.ONE);
        Page<CeamSysRole> ceamSysRolePage = page(page, queryWrapper);
        IPage<CeaMSysRoleVO> ceaMSysRoleVOIPage = PageVOUtil.copyToPageVO(ceamSysRolePage, CeaMSysRoleVO.class);

        for (CeaMSysRoleVO role : ceaMSysRoleVOIPage.getRecords()) {

            List<MenuDTO> menuDTOS = ceamSysMenuService.findByRoleId(role.getId(), GlobalConstants.ZERO);
            // vue3适配选中菜单
            List<Long> collect = menuDTOS.stream().map(MenuDTO::getId).collect(Collectors.toList());
            role.setMenus(collect);
        }

        return ceaMSysRoleVOIPage;
    }

    @Override
    public Collection<SimpleGrantedAuthority> grantedAuthorities(Long userId) {
        List<RoleDTO> roleItemDTOS = this.baseMapper.listByUserId(userId);
        if (CollectionUtils.isEmpty(roleItemDTOS)) {
            throw new ServiceException("没有分配角色");
        }

        List<MenuDTO> menuDTOS = ceamSysMenuService.findByRoles(roleItemDTOS, GlobalConstants.ZERO);

        // 角色
        Set<String> permissions = roleItemDTOS.stream()
                .filter(role -> StringUtils.isNotBlank(role.getName()))
                .map(RoleDTO::getName).collect(Collectors.toSet());
        permissions.addAll(
                menuDTOS.stream()
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(MenuDTO::getPermission).collect(Collectors.toSet())
        );

        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> listByUserId(Long userId) {
        return this.baseMapper.listByUserId(userId);
    }

    @Override
    public void updateMenu(CeaMSysRoleDTO roleDTO, CeamSysRole role) {
        if (roleDTO.getMenus().size() > 0) {
            List<CeamRoleMenu> rolesMenusList = roleDTO.getMenus().stream().map(i -> {
                CeamRoleMenu rolesMenus = new CeamRoleMenu();
                rolesMenus.setRoleId(roleDTO.getId());
                rolesMenus.setMenuId(i);
                return rolesMenus;
            }).collect(Collectors.toList());
            ceamRoleMenuService.remove(new LambdaQueryWrapper<CeamRoleMenu>()
                    .eq(CeamRoleMenu::getRoleId, roleDTO.getId()));
            ceamRoleMenuService.saveBatch(rolesMenusList);
        }
    }

    @Override
    public boolean add(CeaMSysRoleDTO roleDTO) {
        CeamSysRole role = this.getById(roleDTO.getId());

        CeamSysRole roleBy = this.getOne(new LambdaQueryWrapper<CeamSysRole>()
                .eq(CeamSysRole::getName, roleDTO.getName()));

        if (ObjectUtils.isNotEmpty(roleBy) && !roleBy.getId().equals(role.getId())) {
            throw new ServiceException("已存在该角色");
        }
        roleBy = this.getOne(new LambdaQueryWrapper<CeamSysRole>()
                .eq(CeamSysRole::getPermission, roleDTO.getPermission()));
        if (ObjectUtils.isNotEmpty(roleBy) && !roleBy.getId().equals(role.getId())) {
            throw new ServiceException("已存在该角色");
        }
        CeamSysRole ceamSysRole = BeanCopyUtil.copyProperties(roleDTO, CeamSysRole.class);
        ceamSysRole.setEnabled(roleDTO.getEnabled() == null ? GlobalConstants.TRUE : roleDTO.getEnabled());
        ceamSysRole.setDeleted(GlobalConstants.FALSE);
        ceamSysRole.setAddTime(LocalDateTime.now());
        boolean b = this.save(ceamSysRole);
        return b;
    }

    @Override
    public boolean edit(CeaMSysRoleDTO roleDTO) {
        log.info("入参》》》{}", roleDTO);
        CeamSysRole role = this.getById(roleDTO.getId());

        CeamSysRole roleBy = this.getOne(new LambdaQueryWrapper<CeamSysRole>()
                .eq(CeamSysRole::getName, roleDTO.getName()));

        if (ObjectUtils.isNotEmpty(roleBy) && !roleBy.getId().equals(role == null ? null : role.getId())) {
            throw new ServiceException("已存在该角色");
        }
        roleBy = this.getOne(new LambdaQueryWrapper<CeamSysRole>()
                .eq(CeamSysRole::getPermission, roleDTO.getPermission()));
        if (ObjectUtils.isNotEmpty(roleBy) && !roleBy.getId().equals(role == null ? null : role.getId())) {
            throw new ServiceException("已存在该角色");
        }
        role = role == null ? new CeamSysRole() : role;
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setDataScope(roleDTO.getDataScope());

        role.setLevel(roleDTO.getLevel());
        role.setPermission(roleDTO.getPermission());
        boolean b = this.saveOrUpdate(role);
        return b;
    }

    @Override
    public void remove(Set<Long> ids) {
        ids.forEach(id -> {
            LambdaQueryWrapper<CeamRoleMenu> queryWrapper = Wrappers.<CeamRoleMenu>lambdaQuery()
                    .eq(CeamRoleMenu::getRoleId, id);
            CeamRoleMenu ceamRoleMenu = ceamRoleMenuService.getBaseMapper().selectOne(queryWrapper);
            if (ObjectUtils.isNotEmpty(ceamRoleMenu)) {
                throw new ServiceException("该角色下挂着菜单");
            }
            removeById(id);
        });
    }
}
