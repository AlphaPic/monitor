package com.fan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:fanwenlong
 * @date:2018-01-08 15:36:15
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:字符串工具
 * @detail:
 */
public class StringUtils {
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 判断字符串数组是不是存在为空的情况
     * @return
     */
    public static boolean isEmptyExists(String...strs){
        if(strs.length <= 0){
            logger.error("字符串数组为空");
            return false;
        }
        for(String str : strs){
            if(org.springframework.util.StringUtils.isEmpty(str) == true){
                return true;
            }
        }
        return false;
    }
}
