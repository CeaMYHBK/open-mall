package com.ceam.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysJobDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.admin.vo.CeamSysJobVO;

import java.util.Set;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface ICeamSysJobService extends IService<CeamSysJob> {

    IPage<CeamSysJobVO> pageCeamSysJobVO(CeamSysJobDTO data);

    boolean add(CeamSysJobDTO data);

    boolean edit(CeamSysJobDTO data);

    void remove(Set<Long> ids);
}
