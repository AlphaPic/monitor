package com.fan.framework.filter;

import com.fan.consts.InitConfig;
import com.fan.utils.InternetUtils;
import com.fan.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:fanwenlong
 * @date:2017-12-20 20:41:17
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录的过滤器，用来对用户的登录次数进行防控
 */
public class UserLoginFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);

    public static final Boolean Login_button = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info(this.getClass().getName() + "正在初始化");
    }

    /**
     * 在这里对登录的用户进行过滤
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        /** 使用开关进行过滤器开关控制 */
        if(InitConfig.FILTER_LOGIN_BUTTON == false){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String remoteHost = req.getHeader("host");
        if(remoteHost == null || remoteHost.isEmpty() || remoteHost.contains(":") == false){
            logger.error("访问的ip或者端口不能为空");
            res.sendRedirect("LogInfoError.jsp");
        }
        String[] remote = remoteHost.split(":");
        if(remote == null || remote.length != 2){
            logger.error("访问的host格式不对" + remoteHost);
            res.sendRedirect("LogInfoError.jsp");
        }
        String ip    = remote[0];
        Integer port = null;
        try {
            port = Integer.valueOf(remote[1]);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        if(InternetUtils.isIpIlleagle(ip) || InternetUtils.isPortIlleagle(port)){
            logger.error("访问的host或者ip不对,ip:" + ip + ",port:" + port);
            res.sendRedirect("LogInfoError.jsp");
        }
        /** 判断ip的访问次数是不是达到上限，达到的话，则重定向到一个已经超出访问的页面 */
        if(followHostStrategy(ip,port) == false){
            res.sendRedirect("LoginOverTimes.jsp");
        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 判断host是否符合服务器对ip的管控策略
     * 1.一个ip，120秒之内，不能3次调用登录接口
     * 2.未登录的ip，如果在120秒内5次访问需要登录权限的接口，那么本ip在接下来的5分钟内不能访问
     * 3.一个ip在120秒内访问所有接口的次数不能超过40次(不管这个ip里面的接口是不是已经登录)
     * @param ip
     * @param port
     * @return
     */
    private Boolean followHostStrategy(String ip,Integer port){
        String host = ip + "_" + port;
        String interfaceCallInTwoMinute = InitConfig.TWO_MINUTE_INTERFACE_CALL_LIMIT + host;
        String loginPermitInTwoMinute   = InitConfig.TWO_MINUTE_LOGIN_PERMIT_LIMIT + host;
        String loginInTwoMinute         = InitConfig.TWO_MINUTE_LOGIN_LIMIT + host;
        Boolean deleteRes = RedisUtils.deleteIfRedisKeyDoesNotExists(interfaceCallInTwoMinute,loginInTwoMinute,loginPermitInTwoMinute);
        if(deleteRes == false){
            logger.error("redis缓存中可能存在为空的key,interfaceCallInTwoMinute:" + interfaceCallInTwoMinute +
                    ",loginInTwoMinute:" + loginInTwoMinute +
                    ",loginPermitInTwoMinute:" + loginPermitInTwoMinute);
            return false;
        }
        Integer interfaceCallTimes = 0;
        Integer loginPermitCallTimes = 0;
        Integer loginCallTime = 0;
        try {
            interfaceCallTimes = Integer.valueOf(RedisUtils.getRedisValue(interfaceCallInTwoMinute));
            loginPermitCallTimes = Integer.valueOf(RedisUtils.getRedisValue(loginPermitInTwoMinute));
            loginCallTime = Integer.valueOf(RedisUtils.getRedisValue(loginInTwoMinute));
        }catch (Exception e){
            logger.error("解析redis键值的时候出错" + e.getMessage());
            RedisUtils.setStringValue(interfaceCallInTwoMinute,"40",120L);
            RedisUtils.setStringValue(loginPermitInTwoMinute,"40",120L);
            RedisUtils.setStringValue(loginInTwoMinute,"40",120L);
            return false;
        }
        if(interfaceCallTimes > 40 || loginCallTime > 3 || loginPermitCallTimes > 5){
            logger.error("接口调用的条件不满足,在两分钟内,接口调用的情况如下:总调用" +
                    interfaceCallInTwoMinute + "次,登录接口调用" +
                    loginCallTime + "次,登录权限接口调用" +
                    loginPermitInTwoMinute + "次");
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getName() + "正在被销毁");
    }
}
