package com.ceam.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.admin.vo.CeaMSysUserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
public interface ICeamSysUserService extends IService<CeamSysUser> {


    /**
     * 分页查询
     *
     * @param data 入参
     * @return 分页后的数据
     */
    IPage<CeaMSysUserVO> pageCeaMSysUserVO(CeaMSysUserDTO data);

    /**
     * 新增
     * @param data 入参
     * @return true/false
     */
    boolean add(CeaMSysUserDTO data);

    void remove(Set<Long> ids);

    /**
     * 根据账号获取用户，实现做了校验
     *
     * @param userName 账号
     * @return ceaMSysUserDTO
     */
    CeaMSysUserDTO loadUserInfo(String userName);

    /**
     * 根据账号获取用户
     *
     * @param userName 账号
     * @return ceamSysUser
     */
    CeamSysUser getCeamSysUser(String userName);

    /**
     * 修改头像
     * @param multipartFile 文件
     */
    void updateAvatar(MultipartFile multipartFile);

    /**
     * 修改密码
     * @param username 用户名
     * @param encryptPassword 密码
     */
    void updatePass(String username, String encryptPassword);
}
