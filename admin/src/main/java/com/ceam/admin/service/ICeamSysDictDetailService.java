package com.ceam.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysDictDetailDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysDictDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.admin.vo.CeamSysDictDetailVO;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface ICeamSysDictDetailService extends IService<CeamSysDictDetail> {

    IPage<CeamSysDictDetailVO> pageCeamSysDictDetailVO(CeamSysDictDetailDTO data);
}
