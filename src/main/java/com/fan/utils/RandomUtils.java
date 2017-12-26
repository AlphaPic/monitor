package com.fan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author:fanwenlong
 * @date:2017-12-25 19:48:31
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:随机数工具
 */
public class RandomUtils {
    private static final Logger logger = LoggerFactory.getLogger(RandomUtils.class);

    private static Random random;

    static {
        random = new Random(999);
    }

    /**
     * 获取随机的数字
     * 1.判断传参是否非法
     * 2.每次获取1位数的随机数，并将其添加到一个字符串对象中
     * 3.截取长度，并将结果返回
     * @param num
     * @return
     */
    public static String getRandomNumber(int num){
        if(num <= 0){
            return null;
        }
        StringBuilder sb = new StringBuilder(100);
        while (num-- > 0){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(getRandomNumber(4).length());
    }
}
