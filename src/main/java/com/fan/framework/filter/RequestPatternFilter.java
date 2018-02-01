package com.fan.framework.filter;

import com.fan.consts.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.File;
import java.io.IOException;

/**
 * @author:fanwenlong
 * @date:2017-12-26 21:00:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:请求的过滤器
 * @detail:这个过滤器是为了保证以下几点:1.方法中必须包含
 */
public class RequestPatternFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(RequestPatternFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(InitConfig.FILTER_REQUEST_PATTERN_BUTTON == false) {
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
