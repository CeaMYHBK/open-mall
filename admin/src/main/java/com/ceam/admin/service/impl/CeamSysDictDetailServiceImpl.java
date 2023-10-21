package com.ceam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.dto.CeamSysDictDetailDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysDict;
import com.ceam.admin.entity.CeamSysDictDetail;
import com.ceam.admin.mapper.CeamSysDictDetailMapper;
import com.ceam.admin.service.ICeamSysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceam.admin.service.ICeamSysDictService;
import com.ceam.admin.vo.CeamSysDictDetailVO;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
@Service
@AllArgsConstructor
public class CeamSysDictDetailServiceImpl
        extends ServiceImpl<CeamSysDictDetailMapper, CeamSysDictDetail> implements ICeamSysDictDetailService {

    private final ICeamSysDictService sysDictService;

    @Override
    public IPage<CeamSysDictDetailVO> pageCeamSysDictDetailVO(CeamSysDictDetailDTO data) {
        LambdaQueryWrapper<CeamSysDictDetail> queryWrapper = Wrappers.<CeamSysDictDetail>lambdaQuery()
                .orderByDesc(CeamSysDictDetail::getAddTime);

        LambdaQueryWrapper<CeamSysDict> dictWrapper = Wrappers.<CeamSysDict>lambdaQuery()
                .eq(CeamSysDict::getName, data.getDictName());
        CeamSysDict ceamSysDict = sysDictService.getBaseMapper().selectOne(dictWrapper);
        queryWrapper.eq(CeamSysDictDetail::getDictId, ceamSysDict.getId());
        Page<CeamSysDictDetailVO> page = new Page<>();
        page.setCurrent(data.getPage() + GlobalConstants.ONE);
        if (StringUtils.isNotBlank(data.getLabel())) {
            queryWrapper.like(CeamSysDictDetail::getLabel, data.getLabel());
        }
        return baseMapper.pageCeamSysDictDetailVO(page, queryWrapper);
    }
}
