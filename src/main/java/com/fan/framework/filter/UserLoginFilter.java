package com.fan.framework.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author:fanwenlong
 * @date:2017-12-20 20:41:17
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录的过滤器，用来对用户的登录次数进行防控
 */
public class UserLoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("login filter handle  1");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("login filter handle  2");
    }

    @Override
    public void destroy() {
        System.out.println("login filter destroy");
    }
}
