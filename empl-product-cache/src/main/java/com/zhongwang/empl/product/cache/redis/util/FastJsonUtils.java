package com.zhongwang.empl.product.cache.redis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 序列化工具类
 * @author: slliver
 * @date: 2019/9/4 15:55
 * @version: 1.0
 */
public class FastJsonUtils {

    /**
     * 转换object到指定的class对象
     *
     * @param object 要转换的对象
     * @param clazz  转换类型的class对象
     * @param <T>    转换类型
     * @return 转换后的对象
     */
    public static <T> T castObjectToBean(Object object, Class<T> clazz) {
        return TypeUtils.castToJavaBean(object, clazz);
    }

    /**
     * json转list
     *
     * @param object object
     * @param clazz  类型
     * @param <T>    泛型
     * @return List<T>
     */
    public static <T> List<T> castJsonToList(Object object, Class<T> clazz) {
        String str = JSON.toJSONString(object);
        return JSON.parseArray(str, clazz);
    }

    /**
     * 对象转json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String castObjectToJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 校验是否是json字符串
     *
     * @param jsonString json字符串
     * @return 是/否
     */
    public static boolean isJsonString(String jsonString) {
        boolean result = true;
        try {
            JSON.parse(jsonString);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 转换json对象为map
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> castJSONStringToMap(String jsonString) {
        return (Map<String, Object>) JSON.parse(jsonString);
    }

    public static void main(String[] args) {
//        isJsonString("{\"url\":\"http:\\/\\/www.baidu.com\"}");
        boolean result = isJsonString("{\"url\":\"http:\\/\\/www.baidu.com\"");
        System.out.println(result);
    }
}
