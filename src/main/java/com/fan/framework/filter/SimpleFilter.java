package com.fan.framework.filter;

import com.fan.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:fanwenlong
 * @date:2017-12-19 20:47:20
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:
 */
public class SimpleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SimpleFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request   = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer sb = request.getRequestURL();
        if(sb.toString().endsWith("index")){
            ((HttpServletResponse)servletResponse).sendRedirect("home");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
//
        return;
    }

    @Override
    public void destroy() {
        System.out.println("SimpleFilter destroy");
    }
}
