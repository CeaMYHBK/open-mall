package com.ceam.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.ObjectUtils;
import com.ceam.common.utils.SecurityUtils;
import com.ceam.mall.vo.HomeTab;
import com.ceam.shop.service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author CeaM
 * 2023/02/08 17:54
 **/
@Slf4j
@Service
@AllArgsConstructor
public class AppHomeServiceImpl implements IAppHomeService {

    private final ICeamGoodsService goodsService;
    private final ICeamCustomerCouponService customerCouponService;
    private final ICeamCommercialService commercialService;
    private final ICeamGoodsBrandService goodsBrandService;
    private final ICeamGoodsCategoryService goodsCategoryService;
    private final ICeamCouponService couponService;
    private final ICeamHomeMenuService homeMenuService;

    @Override
    public Object home() {
        Long userId = SecurityUtils.getUserId();
        log.info("【请求开始】访问首页,请求参数,userId:{}", userId);

        Map<String, Object> data = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 先查询和用户有关的信息
        Callable<List> couponListCallable = null;

        try {
            if (ObjectUtils.isEmpty(userId)) {
                couponListCallable = () -> couponService.list();
            } else {
                couponListCallable = () -> customerCouponService.listByCustomerId(userId);
            }
            FutureTask<List> couponListTask = new FutureTask<>(couponListCallable);
            executorService.submit(couponListTask);

            // 优先从缓存中读取
            if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
                data = HomeCacheManager.getCacheData(HomeCacheManager.INDEX);
                if (data != null) {// 加上这个判断，排除判断后到获取数据之间时间段清理的情况
                    LocalDateTime expire = (LocalDateTime) data.get("expireTime");
                    log.info("访问首页,存在缓存数据，除用户优惠券信息外，加载缓存数据,有效期时间点："+ expire.toString());
                    data.put("couponList", couponListTask.get());
                    return data;
                }
            }

            Callable<List> bannerListCallable = () -> commercialService.list();

            Callable<List> channelListCallable = () -> homeMenuService.listCeamHomeMenuVO();

            Callable<List> newGoodsListCallable = () -> goodsService
                    .queryNewOrHost(0, 10, 0).getRecords();

            Callable<List> hotGoodsListCallable = () -> goodsService
                    .queryNewOrHost(0, 10, 1).getRecords();

            Callable<List> brandListCallable = () -> goodsBrandService.list();

            FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
            FutureTask<List> channelTask = new FutureTask<>(channelListCallable);
            FutureTask<List> newGoodsListTask = new FutureTask<>(newGoodsListCallable);
            FutureTask<List> hotGoodsListTask = new FutureTask<>(hotGoodsListCallable);
            FutureTask<List> brandListTask = new FutureTask<>(brandListCallable);

            executorService.submit(bannerTask);
            executorService.submit(channelTask);
            executorService.submit(newGoodsListTask);
            executorService.submit(hotGoodsListTask);
            executorService.submit(brandListTask);

            data.put("banners", bannerTask.get());
            data.put("channels", channelTask.get());
            data.put("coupons", couponListTask.get());
            data.put("newGoodsList", newGoodsListTask.get());
            data.put("hotGoodsList", hotGoodsListTask.get());
            data.put("brands", brandListTask.get());

            HomeTab newTab = new HomeTab(0, "新品", data.get("newGoodsList"));
            //Map<HomeTab, Object> hashMap = new HashMap<>();
            //hashMap.put(newTab, data.get("newGoodsList"));
            HomeTab hotTab = new HomeTab(1, "热销", data.get("hotGoodsList"));
            //hashMap.put(hotTab, data.get("hotGoodsList"));
            //log.info("map map>>>>{}", hashMap);
            List<Object> list = new ArrayList<>();
            list.add(newTab);
            list.add(hotTab);
            data.put("tabs", list);

            // 缓存数据首页缓存数据
            HomeCacheManager.loadData(HomeCacheManager.INDEX, data);
            executorService.shutdown();
        } catch (Exception e) {
            log.error("request error", e);
        }

        log.info("【请求结束】访问首页,响应结果,优惠券信息：{}", JSONObject.toJSONString(data.get("couponList")));

        return data;
    }

    @Data
    @AllArgsConstructor
    private class Tab {
        private int current;
        private String tabName;
    }
}
