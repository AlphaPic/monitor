package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2017-12-20 21:33:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:初始化的配置
 */
public interface InitConfig {
    /** 过滤器开关控制 */
    public final Boolean Login_button = false;  //登录过滤器的开关控制

    /** JSP viewResolver的匹配 */
    public final String JspResolverPrefix = "/WEB-INF/";        //前缀
    public final String JspResolverSuffix = ".jsp";             //后缀

    /** 登录拦截器匹配路径 */
    public final String LoginInterceptorPattern_0 = "/*";

    /** 正则匹配 */
    public final String ip4Pattern = "([1-9]|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}";//是否是正确的ip

    /** redis的相关配置 */
    public final Long userSurviveTime = 1800L;  //用户的存活时间

    /** redis常量 */
    public final String LOGIN_FAIL = "LOGIN_FAIL_";        //登录失败次数
    public final String LIMIT_HOST = "LIMIT_HOST_";        //受限制的host
    //登录相关
    public final String TWO_MINUTE_LOGIN_LIMIT = "TWO_MINUTE_LOGIN_LIMIT_";                     //两分钟内的登录接口调用限制
    public final String TWO_MINUTE_LOGIN_PERMIT_LIMIT = "TWO_MINUTE_LOGIN_PERMIT_LIMIT_";       //两分钟内的登录权限接口调用限制
    public final String TWO_MINUTE_INTERFACE_CALL_LIMIT = "TWO_MINUTE_INTERFACE_CALL_LIMIT_";   //两分钟内的接口调用限制
}
