package com.zhongwang.support.util;

import java.util.UUID;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 16:17
 * @version: 1.0
 */
public final class UUIDUtil {

    public UUIDUtil() {

    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
