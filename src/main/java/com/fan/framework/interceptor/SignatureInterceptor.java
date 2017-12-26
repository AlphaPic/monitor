package com.fan.framework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:fanwenlong
 * @date:2017-12-26 20:59:40
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:签名拦截器
 * @detail:这个拦截器主要用来查看请求的完整性
 */
public class SignatureInterceptor implements HandlerInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(SignatureInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
