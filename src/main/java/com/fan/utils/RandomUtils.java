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

    private static final char[] chars = {'a','b','c','d','e','f','g',
                                         'h','i','j','k','l','m','n',
                                         'o','p','q','r','s','t',
                                         'u','v','w','x','y','z',
                                         '_'};

    private static final char[] hexChars = {'0','1','2','3',
                                            '4','5','6','7',
                                            '8','9','a','b',
                                            'c','d','e','f'};
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

    /**
     * 获取随机的16进制号码
     * @param num
     * @return
     */
    public static String getRandomHexNumber(int num){
        if(num < 0){
            return null;
        }
        StringBuilder sb = new StringBuilder(100);
        while (num-- > 0){
            sb.append(hexChars[random.nextInt(16)]);
        }
        return sb.toString();
    }

    /**
     * 获取cookie
     * @return
     */
    public static String getAlphaCookie(){
        StringBuilder sb = new StringBuilder(30);
        int len = random.nextInt(50);
        while (len < 47){
            len = random.nextInt(50);
        }
        for(int i = 0;i < len;i++){
            int val = random.nextInt(77);
            if(val > 26){
                sb.append((val - 26) % 10);
            }else {
                sb.append(chars[val]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(getRandomHexNumber(256).toUpperCase());
    }
}
