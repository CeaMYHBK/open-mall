package com.ceam.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author CeaM
 * 2023/01/28 11:42
 **/
@Slf4j
public class PageVOUtil {

    public static <O, T> IPage<T> copyToPageVO(Page<O> oPage, Class<T> clazz) {
        log.info(">>>>current：{}，size：{}", oPage.getCurrent(), oPage.getSize());

        List<T> vos = BeanCopyUtil.copyListProperties(oPage.getRecords(), clazz);
        Page<T> pageVO = new Page<>(oPage.getCurrent(), oPage.getSize(), oPage.getTotal());
        log.info("vos>>>>>>{}", vos);
        // 对list分隔，根据offset以及size划分，可能有bug
        // total/size < 1 ? total%size
        // page*size > total 取余

        pageVO.setRecords(vos);
        //pageVO.setTotal(oPage.getTotal());
        return pageVO;
    }
}
