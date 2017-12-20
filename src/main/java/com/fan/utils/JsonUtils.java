package com.fan.utils;

import com.google.gson.Gson;

/**
 * @author:fanwenlong
 * @date:2017-12-02 16:15:33
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:json的处理工具
 */
public class JsonUtils {
    private static Gson gson = new Gson();

    /**
     * 转化为json字符串
     * @param src
     * @return
     */
    public static String toJson(Object src){
        if(src != null){
            return gson.toJson(src);
        }
        return null;
    }

    /**
     * 转化为对象
     * @param src
     * @param clazz
     * @return
     */
    public static Object toObject(String src,Class clazz){
        if(src != null){
            try {
                return gson.fromJson(src, clazz);
            }catch (Exception e){
            }
        }
        return null;
    }
}
