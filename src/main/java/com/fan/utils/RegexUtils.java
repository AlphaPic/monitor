package com.fan.utils;

import com.fan.consts.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @author:fanwenlong
 * @date:2018-01-08 11:33:10
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:正则表达式工具
 * @detail:
 */
public class RegexUtils {
    private static final Logger logger = LoggerFactory.getLogger(RegexUtils.class);

    /**
     * 判断是不是手机
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        Pattern pattern = Pattern.compile(InitConfig.mobilePattern);
        return pattern.matcher(mobile).matches();
    }

    /**
     * 判断是不是邮件
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile(InitConfig.emailPattern);
        return pattern.matcher(email).matches();
    }

    public static void main(String[] args){
        String email = "1693293713@qq.com";
        String mobile = "13599990000";
        System.out.println(email + " is email?" + isEmail(email));
        System.out.println(mobile + " is mobile?" + isMobile(mobile));
    }
}
