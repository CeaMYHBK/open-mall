package com.ceam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.CeaMSysRoleDTO;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysRole;
import com.ceam.admin.entity.CeamSysUser;
import com.ceam.admin.entity.CeamUserRole;
import com.ceam.admin.mapper.CeamSysUserMapper;
import com.ceam.admin.service.ICeamSysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.admin.service.ICeamUserRoleService;
import com.ceam.admin.vo.CeaMSysRoleVO;
import com.ceam.admin.vo.CeaMSysUserVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@Slf4j
@Service
public class CeamSysUserServiceImpl
        extends ServiceImpl<CeamSysUserMapper, CeamSysUser> implements ICeamSysUserService {
    @Value("${file.avatar}")
    private String avatar;

    private final ICeamUserRoleService userRoleService;

    public CeamSysUserServiceImpl(ICeamUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public IPage<CeaMSysUserVO> pageCeaMSysUserVO(CeaMSysUserDTO data) {
        LambdaQueryWrapper<CeamSysUser> queryWrapper = Wrappers.<CeamSysUser>lambdaQuery()
                .eq(CeamSysUser::getDeleted, GlobalConstants.FALSE);
        if (StringUtils.isNotBlank(data.getNickname())) {
            queryWrapper.like(CeamSysUser::getNickname, data.getNickname());
        }
        if (StringUtils.isNotBlank(data.getUsername())) {
            queryWrapper.like(CeamSysUser::getUsername, data.getUsername());
        }
        if (ObjectUtils.isNotEmpty(data.getDeptId())) {
            queryWrapper.eq(CeamSysUser::getDeptId, data.getDeptId());
        }
        if (ObjectUtils.isNotEmpty(data.getStatus())) {
            queryWrapper.eq(CeamSysUser::getStatus, data.getStatus());
        }
        Page<CeamSysUser> page = new Page<>(data.getPage(), data.getSize());
        page.setCurrent((long)data.getPage() - GlobalConstants.ONE);
        Page<CeamSysUser> ceamSysUserPage = page(page, queryWrapper);
        IPage<CeaMSysUserVO> ceaMSysUserVOIPage = PageVOUtil
                .copyToPageVO(ceamSysUserPage, CeaMSysUserVO.class);
        return ceaMSysUserVOIPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(CeaMSysUserDTO data) {
        CeamSysUser userName = this.getOne(new LambdaQueryWrapper<CeamSysUser>()
                .eq(CeamSysUser::getUsername, data.getUsername()));
        if (userName != null) {
            throw new ServiceException("已存在该账号");
        }
        CeamSysUser userEmail = this.getOne(new LambdaQueryWrapper<CeamSysUser>()
                .eq(CeamSysUser::getEmail, data.getEmail()));
        if (userEmail != null) {
            throw new ServiceException("已存在该邮箱");
        }
        CeamSysUser ceamSysUser = BeanCopyUtil.copyProperties(data, CeamSysUser.class);
        if (ObjectUtils.isNotEmpty(data.getDept())) {
            ceamSysUser.setDeptId(data.getDept().getId());
        }
        if (ObjectUtils.isNotEmpty(data.getJob())) {
            ceamSysUser.setJobId(data.getJob().getId());
        }
        ceamSysUser.setStatus(GlobalConstants.ZERO);
        ceamSysUser.setDeleted(GlobalConstants.FALSE);
        ceamSysUser.setAddTime(LocalDateTime.now());
        boolean result = this.save(ceamSysUser);

        CeamUserRole usersRoles = new CeamUserRole();
        usersRoles.setUserId(ceamSysUser.getId());
        Set<CeaMSysRoleDTO> set = data.getRoles();
        for (CeaMSysRoleDTO roleIds : set) {
            usersRoles.setRoleId(roleIds.getId());
        }
        if (result) {
            userRoleService.save(usersRoles);
        }
        return result;
    }

    @Override
    public void remove(Set<Long> ids) {
        ids.forEach(id -> {
            removeById(id);
        });
    }

    @Override
    public CeaMSysUserDTO loadUserInfo(String userName) {
        LambdaQueryWrapper<CeamSysUser> queryWrapper = Wrappers.<CeamSysUser>lambdaQuery()
                .eq(CeamSysUser::getUsername, userName);
        CeamSysUser ceamSysUser = baseMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(ceamSysUser)) {
            throw new ServiceException("账号不存在");
        }
        if (ceamSysUser.getStatus() == GlobalConstants.ONE) {
            throw new ServiceException("账号已禁用");
        }
        CeaMSysUserDTO ceaMSysUserDTO = BeanCopyUtil.copyProperties(ceamSysUser, CeaMSysUserDTO.class);
        return ceaMSysUserDTO;
    }

    @Override
    public CeamSysUser getCeamSysUser(String userName) {
        LambdaQueryWrapper<CeamSysUser> queryWrapper = Wrappers.<CeamSysUser>lambdaQuery()
                .eq(CeamSysUser::getUsername, userName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateAvatar(MultipartFile multipartFile) {
        log.info("username>>>{}", SecurityUtils.getUsername());
        CeamSysUser user = this.getOne(new LambdaQueryWrapper<CeamSysUser>()
                .eq(CeamSysUser::getUsername, SecurityUtils.getUsername()));

        String oldPath = "";
        if (user != null && StringUtils.isNotBlank(user.getAvatar())) {
            oldPath = user.getAvatar();
        }
        File file = FileUtil.upload(multipartFile, avatar);
        assert file != null;
        user.setAvatar(file.getName());
        this.saveOrUpdate(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
    }

    @Override
    public void updatePass(String username, String encryptPassword) {
        LambdaUpdateWrapper<CeamSysUser> updateWrapper = Wrappers.<CeamSysUser>lambdaUpdate()
                .set(CeamSysUser::getPassword, encryptPassword)
                .eq(CeamSysUser::getUsername, username);
        update(updateWrapper);
    }
}
