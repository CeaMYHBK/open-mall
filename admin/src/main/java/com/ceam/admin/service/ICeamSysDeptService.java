package com.ceam.admin.service;

import com.ceam.admin.dto.CeamSysDeptDTO;
import com.ceam.admin.entity.CeamSysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface ICeamSysDeptService extends IService<CeamSysDept> {

    List<CeamSysDept> queryAll(CeamSysDeptDTO data);

    /**
     * 构建部门树
     *
     * @param data 入参
     * @return object
     */
    Object buildTree(List<CeamSysDeptDTO> data);

    /**
     * 批量移除
     *
     * @param ids 部门ID集合
     */
    void remove(Set<Long> ids);

    /**
     * 根据PID查询
     * @param pid /
     * @return /
     */
    List<CeamSysDept> findByPid(long pid);

    /**
     * 删除部门
     * @param deptIds
     */
    void delDepts(List<Long> deptIds);
}
