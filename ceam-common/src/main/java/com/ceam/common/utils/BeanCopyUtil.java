package com.ceam.common.utils;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author CeaM
 * 2023/01/27 22:04
 **/
public class BeanCopyUtil extends BeanUtils {


    /**
     * A 转换为 B，字段类型相同才转换，除非做处理
     * @param originObj 原始对象
     * @param clazz 目标对象的class，及B.class
     * @return 目标对象
     */
    public static  <O, T> T copyProperties(O originObj, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            copyProperties(originObj, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static  <O, T> List<T> copyListProperties(List<O> originObjs, Class<T> clazz) {
        return Optional.of(originObjs)
                .orElse(Lists.newArrayList())
                .stream().map(o -> copyProperties(o, clazz))
                .collect(Collectors.toList());

    }

    @Data
    private static class A {
        String id;
    }

    @Data
    private static class B {
        String id;
    }

    public static void main(String[] args) {
        A a1 = new A();
        a1.setId("133");
        A a2 = new A();
        a2.setId("143");
        List<A> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        List<B> bList = copyListProperties(list, B.class);
        //B bList = copyProperties(a1, B.class);
        System.out.println(bList);
    }
}
