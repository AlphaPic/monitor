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
    public final Boolean FILTER_LOGIN_BUTTON           = false;  //登录过滤器的开关控制(true打开,false关闭)
    public final Boolean FILTER_REQUEST_PATTERN_BUTTON = false;  //请求表达式过滤器开关控制(true打开,false关闭)
    public final Boolean FILTER_SIMPLE_BUTTON          = false;  //简单的过滤器控制(true打开,false关闭)

    /** 拦截器开关控制 */
    public final Boolean INTERCEPTOR_LOGIN_BUTTON      = false;  //登录拦截器开关控制(true打开,false关闭)
    public final Boolean INTERCEPTOR_SIGNATURE_BUTTON  = false;  //签名拦截器的开关控制(true打开,false关闭)

    /** JSP viewResolver的匹配 */
    public final String JspResolverPrefix = "/WEB-INF/";        //前缀
    public final String JspResolverSuffix = ".jsp";             //后缀

    /** 登录拦截器匹配路径 */
    public final String LoginInterceptorPattern_0 = "/*";
    public final String SignatureInterceptorPattern = "/*";

    /** 正则匹配 */
    public final String ip4Pattern = "([1-9]|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}";//是否是正确的ip

    /** redis的相关配置 */
    public final Long userSurviveTime = 1800L;             //用户cookie的存活时间

    /** redis常量 */
    public final String LOGIN_FAIL  = "LOGIN_FAIL_";        //登录失败次数
    public final String LIMIT_HOST  = "LIMIT_HOST_";        //受限制的host
    public final String COOKIE_ID   = "COOKIE_ID_";         //用户状态(一般拿cookie来获取userId)

    /** 登录相关 */
    public final String TWO_MINUTE_LOGIN_LIMIT = "TWO_MINUTE_LOGIN_LIMIT_";                     //两分钟内的登录接口调用限制
    public final String TWO_MINUTE_LOGIN_PERMIT_LIMIT = "TWO_MINUTE_LOGIN_PERMIT_LIMIT_";       //两分钟内的登录权限接口调用限制
    public final String TWO_MINUTE_INTERFACE_CALL_LIMIT = "TWO_MINUTE_INTERFACE_CALL_LIMIT_";   //两分钟内的接口调用限制

    /** signal的签名值,使用这个可以免去校验签名 */
    public final String SIGNATURE = "e878766-12a1-9b766e";
    /** MD5的盐 */
    public final String MD5_SALT="AKSDHAHSDFAZXVBAAJWKERGHQFSKDMABLFAJKHSJKDFHIAWYHERIOOHFSDJKBFKHDAKL";


    /** 是否允许debug模式 */
    public final Boolean DEBUG_MODE = true;

    /** ------------------------------------------------Context上下文配置-------------------------------------------------- **/
    /** redis连接池相关配置 */
    public final Integer REDIS_MAXIDLE      = 100;      /** 最大的空闲数 */
    public final Integer REDIS_MAXACTIVE    = 100;      /** 最大的活跃数 */
    public final Integer REDIS_MAXWAIT      = 1000;     /** 最长等待时间 */
    public final Boolean REDIS_TESTONBORROW = true;     /** 未知 */

    public final String REDIS_HOST  = "localhost";       /** redis主机ip */
    public final Integer REDIS_PORT = 6379;              /** redis端口 */
    public final String REDIS_AUTH  = null;              /** 密码 */
    public final Integer REDIS_TIMEOUT = 100;            /** 超时时间 */

    /** 数据库相关配置 */
    /** Mysql */
    public final String MYSQL_DRIVER    = "com.mysql.jdbc.Driver";                  /** mysql驱动名称 */
    public final String MYSQL_URL       = "jdbc:mysql://127.0.0.1:3306/monitor";    /** mysql驱动url */
    public final String MYSQL_USERNAME  = "root";                                   /** mysql用户名 */
    public final String MYSQL_PASSWORD  = "xiwang1992";                                 /** mysql密码 */

    public final String MYSQL_ENVIRONMENT   = "mysqlEnvironment";                   /** mysql的环境名称 */
}
