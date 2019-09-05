package com.zhongwang.empl.product.cache.redis.util;

import com.zhongwang.empl.product.cache.redis.SimpleRedis;
import com.zhongwang.support.common.spring.SpringContextHolder;
import org.apache.shiro.cache.CacheException;

/**
 * @Description: Redis工具类
 * @author: slliver
 * @date: 2019/9/5 10:53
 * @version: 1.0
 */
public class RedisUtils {

    private static SimpleRedis simpleRedis = SpringContextHolder.getBean(SimpleRedis.class);

    /**
     * 手动设置缓存，expire=0时，表示使用全局默认时间
     *
     * @param key
     * @param value
     * @param expire
     * @throws CacheException
     */
    public static <K, V> V set(String key, V value, int expire) throws CacheException {
        try {
            simpleRedis.set(key, value, expire);
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    public static Object get(String key) throws CacheException {
        try {
            return simpleRedis.get(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    public static <T> T getValue(String key, Class<T> classzz) throws CacheException {
        try {
            return FastJsonUtils.castObjectToBean(get(key), classzz);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 删除
     * @param key
     * @throws CacheException
     */
    public static void remove(String... key) throws CacheException {
        try {
            simpleRedis.del(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
