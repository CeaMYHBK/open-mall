package com.ceam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.CeamSysJobDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysJob;
import com.ceam.admin.mapper.CeamSysJobMapper;
import com.ceam.admin.service.ICeamSysJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.admin.vo.CeamSysJobVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@Service
public class CeamSysJobServiceImpl
        extends ServiceImpl<CeamSysJobMapper, CeamSysJob> implements ICeamSysJobService {

    @Override
    public IPage<CeamSysJobVO> pageCeamSysJobVO(CeamSysJobDTO data) {
        LambdaQueryWrapper<CeamSysJob> queryWrapper = Wrappers.<CeamSysJob>lambdaQuery()
                .orderByDesc(CeamSysJob::getAddTime);
        Page<CeamSysJobVO> page = new Page<>();
        page.setCurrent((data.getPage()) + GlobalConstants.ONE);
        page.setSize(data.getSize());
        if (StringUtils.isNotBlank(data.getName())) {
            queryWrapper.like(CeamSysJob::getName, data.getName());
        }
        if (ObjectUtils.isNotEmpty(data.getDeptId())) {
            queryWrapper.eq(CeamSysJob::getDeptId, data.getDeptId());
        }
        if (ObjectUtils.isNotEmpty(data.getEnabled())) {
            queryWrapper.eq(CeamSysJob::getEnabled, data.getEnabled());
        }
        IPage<CeamSysJobVO> ceamSysJobVOIPage = baseMapper.pageCeamSysJobVO(page, queryWrapper);
        return ceamSysJobVOIPage;
    }

    @Override
    public boolean add(CeamSysJobDTO data) {
        data.setDeleted(GlobalConstants.FALSE);
        CeamSysJob ceamSysJob = BeanCopyUtil.copyProperties(data, CeamSysJob.class);
        ceamSysJob.setAddTime(LocalDateTime.now());
        ceamSysJob.setDeptId(data.getDept().getId());
        boolean b = save(ceamSysJob);
        return b;
    }

    @Override
    public boolean edit(CeamSysJobDTO data) {
        CeamSysJob ceamSysJob = BeanCopyUtil.copyProperties(data, CeamSysJob.class);
        ceamSysJob.setDeptId(data.getDept().getId());
        boolean b = updateById(ceamSysJob);
        return b;
    }

    @Override
    public void remove(Set<Long> ids) {
        ids.forEach(id -> {
            removeById(id);
        });
    }
}
