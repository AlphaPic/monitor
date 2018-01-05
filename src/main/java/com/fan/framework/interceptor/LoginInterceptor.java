package com.fan.framework.interceptor;

import com.fan.consts.InitConfig;
import com.fan.consts.SystemErrorInfo;
import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.model.basicService.User;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author:fanwenlong
 * @date:2017-12-20 20:54:41
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:登录拦截器
 * @detail:登录拦截需要对用户进行拦截
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private IUserDBService userDBService;


    /**
     * 登录拦截器预处理
     * 默认前提:到了这里的时候，得到的请求已经默认都是合法的了
     * 处理逻辑:
     * 1.首先从缓存之中拿到用户的cookie,拿不到的话，直接返回false
     * 2.拿到的话,则从cookie中解析出userId的信息,解析不出来返回false
     * 3.用解析出来的userId去数据库中获取User,拿不到的话返回false
     * 4.判断用户是否是处于冻结状态,是的话则返回false
     * 5.假如可以拿到用户的数据,则将用户的信息设置到request中去
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        /** 开关打开才进入处理，否则直接返回true */
        if(InitConfig.INTERCEPTOR_LOGIN_BUTTON == false){
            return true;
        }
        /** 这一步骤实现上述的1,2两步 */
        Integer userId = getUserIdFromCache(request);
        if(userId == -1){
            logger.error("获取用户id失败");
            return false;
        }
        User user = userDBService.getUser(userId);
        if(user == null){
            logger.error("没有获取到用户的数据");
            return false;
        }
//        if("FROZEN".equalsIgnoreCase(user.getStatus())){
//            logger.error("当前用户已冻结" + user);
//            return false;
//        }
        /** 设置request的值 */
        request.setAttribute("name",user.getUserName());
        request.setAttribute("mobile",user.getMobile());
        request.setAttribute("email",user.getEmail());
//        request.setAttribute("permit",user.getPermit());
        return true;
    }

    /** 通过cookie从缓存中获取用户的相关数据 */
    private Integer getUserIdFromCache(HttpServletRequest request){
        String cookieValue = null;
        String cookieName = "monitorCookie";
        /** 获取cookie的值 */
        if(request.getHeader(cookieName) != null){
            cookieValue = request.getHeader(cookieName);
        }else if(request.getAttribute(cookieName) != null){
            cookieValue = (String) request.getAttribute(cookieName);
        }else if(request.getParameter(cookieName) != null){
            cookieValue = request.getParameter(cookieName);
        }else {
            /** 从传过来的cookie中去拿 */
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookieName.equalsIgnoreCase(cookie.getName())){
                    cookieValue = cookie.getValue();
                }
            }
        }
        if(StringUtils.isEmpty(cookieValue)){
            logger.error("没有cookie");
            return -1;
        }
        /** 利用cookie来找到userId */
        String redisKey = InitConfig.COOKIE_ID + cookieValue;
        Integer userId = 0;
        try{
            String userIdString = redisTemplate.opsForValue().get(redisKey);
            userId = Integer.parseInt(userIdString);
            if(userId <= 0){
                redisTemplate.delete(redisKey);
                return -1;
            }
        }catch (Exception e){
            logger.error("获取userId时发生异常" + redisKey);
        }
        return userId;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        /** 在这里发一个告知用户登录成功的异步消息(待完成) */
    }
}
