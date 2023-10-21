package com.ceam.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceam.admin.entity.CeamSysJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceam.admin.vo.CeamSysJobVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface CeamSysJobMapper extends BaseMapper<CeamSysJob> {

    IPage<CeamSysJobVO> pageCeamSysJobVO(Page page,
                                         @Param(Constants.WRAPPER) Wrapper wrapper);
}
