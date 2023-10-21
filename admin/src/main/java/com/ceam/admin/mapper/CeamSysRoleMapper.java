package com.ceam.admin.mapper;

import com.ceam.admin.dto.RoleDTO;
import com.ceam.admin.entity.CeamSysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
public interface CeamSysRoleMapper extends BaseMapper<CeamSysRole> {

    List<RoleDTO> listByUserId(@Param("userId") Long userId);
}
