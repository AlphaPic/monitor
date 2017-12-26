package com.fan.utils;

import com.fan.consts.BasicEnum;
import com.fan.consts.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:fanwenlong
 * @date:2017-12-25 19:59:34
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:网络工具
 */
public class InternetUtils {
    private static final Logger logger = LoggerFactory.getLogger(InternetUtils.class);

    /**
     * 判断ip是否合理
     * @param ip
     * @return
     */
    public static Boolean isIpIlleagle(String ip){
        /** 匹配的模式 */
        Pattern pattern = Pattern.compile(InitConfig.ip4Pattern);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /**
     * 判断通道是不是合法
     * @param channel
     * @return
     */
    public static Boolean isChannelIlleagle(String channel){
        if(BasicEnum.LOGIN_CHANNEL.ANDROID.toString().equals(channel)
                || BasicEnum.LOGIN_CHANNEL.IOS.toString().equals(channel)
                || BasicEnum.LOGIN_CHANNEL.TOUCH.toString().equals(channel)){
            return true;
        }
        return false;
    }

    /**
     * 判断用户名是否合法
     * @param username
     * @return
     */
    public static Boolean isUserNameIlleagle(String username){
        return true;
    }

    /**
     * 判断密码是否合法
     * @param password
     * @return
     */
    public static Boolean isPasswordIlleagle(String password){
        return true;
    }
}
