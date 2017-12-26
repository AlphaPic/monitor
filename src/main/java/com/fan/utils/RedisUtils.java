package com.fan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:fanwenlong
 * @date:2017-12-25 20:18:04
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:redis工具类
 */
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * 设置string类型的redis值
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public static Boolean setStringValue(String key,String value,Long timeout){
        return true;
    }

    /**
     * 刷新用户的存活时间,这个留给拦截器调用
     * @param cookie
     * @param time
     */
    public static void refreshCookieTimeout(String cookie,Long time){
    }

    /**
     * 判断redis的key是不是存在
     * 返回值为什么是String而不是Boolean的原因是为了保证对与redis key的操作可以在一个流程中完成
     * 是因为存在一种临界值情况，当我们在redis key快要过期的时候调用本方法时，其值是true，但是
     * 我们获取redis的值的时候，其值确是空，为了避免这种情况，我们不两次获取redis中的值
     * @param key
     * @return
     */
    public static String redisKeyExist(String key){
        return null;
    }

    /**
     * 删除redis中的key
     * @param key
     * @return
     */
    public static Boolean deleteRedisKey(String key){
        return false;
    }
}
