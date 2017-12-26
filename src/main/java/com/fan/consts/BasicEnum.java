package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2017-12-25 19:54:14
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:基本的枚举
 */
public class BasicEnum {
    /** 登录通道 */
    public static enum  LOGIN_CHANNEL{
        TOUCH,
        ANDROID,
        IOS;
    }

    /** 用户的登录状态 */
    public static enum LOGIN_STATUS{
        LOGIN,
        LOGOUT;
    }

    public static void main(String[] args){
        System.out.println(LOGIN_CHANNEL.ANDROID.toString());
    }
}
