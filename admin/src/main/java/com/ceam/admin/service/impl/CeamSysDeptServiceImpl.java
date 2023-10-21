package com.ceam.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.admin.dto.CeamSysDeptDTO;
import com.ceam.admin.entity.CeamSysDept;
import com.ceam.admin.entity.CeamSysJob;
import com.ceam.admin.mapper.CeamSysDeptMapper;
import com.ceam.admin.mapper.CeamSysJobMapper;
import com.ceam.admin.service.ICeamSysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@Service
@AllArgsConstructor
public class CeamSysDeptServiceImpl
        extends ServiceImpl<CeamSysDeptMapper, CeamSysDept> implements ICeamSysDeptService {

    private final CeamSysJobMapper jobMapper;

    @Override
    public List<CeamSysDept> queryAll(CeamSysDeptDTO data) {
        LambdaQueryWrapper<CeamSysDept> queryWrapper = Wrappers.<CeamSysDept>lambdaQuery()
                .eq(CeamSysDept::getDeleted, GlobalConstants.FALSE);
        if (StringUtils.isNotBlank(data.getName())) {
            queryWrapper.like(CeamSysDept::getName, data.getName());
        }
        if (ObjectUtils.isNotEmpty(data.getEnabled())) {
            queryWrapper.eq(CeamSysDept::getEnabled, data.getEnabled());
        }
        List<CeamSysDept> ceamSysDepts = baseMapper.selectList(queryWrapper);
        return ceamSysDepts;
    }

    @Override
    public Object buildTree(List<CeamSysDeptDTO> data) {
        Set<CeamSysDeptDTO> trees = new LinkedHashSet<>();
        Set<CeamSysDeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = data.stream().map(CeamSysDeptDTO::getName).collect(Collectors.toList());
        boolean isChild;
        List<CeamSysDept> deptList = this.queryAll(new CeamSysDeptDTO());
        for (CeamSysDeptDTO deptDTO : data) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (CeamSysDeptDTO it : data) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if (isChild) {
                depts.add(deptDTO);
                for (CeamSysDept dept : deptList) {
                    if (dept.getId().equals(deptDTO.getPid()) && !deptNames.contains(dept.getName())) {
                        depts.add(deptDTO);
                    }
                }
            }
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = data.size();

        Map<String, Object> map = new HashMap<>(2);
        map.put("total", totalElements);
        map.put("records", CollectionUtils.isEmpty(trees) ? data : trees);
        return map;
    }

    @Override
    public void remove(Set<Long> ids) {
        List<Long> deptIds = new ArrayList<>();
        for (Long id : ids) {
            List<CeamSysDept> deptList = findByPid(id);
            CeamSysDept dept = getOne(new LambdaQueryWrapper<CeamSysDept>().eq(CeamSysDept::getId, id));
            if (null != dept) {
                deptIds.add(dept.getId());
            }
            if (CollectionUtil.isNotEmpty(deptList)) {
                for (CeamSysDept d : deptList) {
                    deptIds.add(d.getId());
                }
            }
        }

        delDepts(deptIds);
    }

    @Override
    public List<CeamSysDept> findByPid(long pid) {
        LambdaQueryWrapper<CeamSysDept> queryWrapper = Wrappers.<CeamSysDept>lambdaQuery()
                .eq(CeamSysDept::getPid, pid);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void delDepts(List<Long> deptIds) {
        Long jobCount = jobMapper.selectCount(Wrappers.<CeamSysJob>lambdaQuery().in(CeamSysJob::getDeptId, deptIds));

        if (jobCount > 0) {
            throw new ServiceException("所选部门中存在与岗位关联，请取消关联后再试");
        }
//        if (roleCount > 0) {
//            throw new ServiceException("所选部门中存在与角色关联，请取消关联后再试");
//        }
        this.removeByIds(deptIds);
    }
}
