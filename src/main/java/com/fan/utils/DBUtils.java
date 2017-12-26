package com.fan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:fanwenlong
 * @date:2017-12-25 21:37:10
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:数据库工具
 */
public class DBUtils {
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /**
     * 判断用户是不是合法
     * @param userName
     * @param passwordHash
     * @return
     */
    public static Boolean userIlleagle(String userName,String passwordHash){
        return true;
    }

    /**
     * 获取用户信息
     * @return
     */
    public static Object getUserInfo(Long id){
        return null;
    }
}
