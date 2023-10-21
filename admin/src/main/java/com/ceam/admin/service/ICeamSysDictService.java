package com.ceam.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeamSysDictDTO;
import com.ceam.admin.dto.PageableDTO;
import com.ceam.admin.entity.CeamSysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceam.admin.vo.CeamSysDictVO;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author CeaM
 * @since 2023-01-29
 */
public interface ICeamSysDictService extends IService<CeamSysDict> {

    IPage<CeamSysDictVO> pageCeamSysDictVO(CeamSysDictDTO data);
}
