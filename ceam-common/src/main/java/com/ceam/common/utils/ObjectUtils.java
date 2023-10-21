package com.ceam.common.utils;

import org.springframework.lang.Nullable;

/**
 * @author CeaM
 * 2023/01/27 10:16
 **/
public class ObjectUtils extends org.springframework.util.ObjectUtils {

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }
}
