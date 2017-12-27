package com.fan.framework.config;

import com.fan.framework.filter.RequestPatternFilter;
import com.fan.framework.filter.SimpleFilter;
import com.fan.framework.filter.UserLoginFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.Filter;

/**
 * @author:fanwenlong
 * @date:2017-12-02 11:54:53
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:Spring MVC的初始化类
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    /**
     *设置上下文
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 设置web的相关配置
     * 这个配置用来处理WebConfig配置类中的bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 设置映射的字符串
     * 设置Spring MVC的上下文监听器
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加过滤器链，请求将在这里被过滤
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                            new UrlRewriteFilter(),         /** 实现请求的重定向过滤器(用于接口的版本控制) */
                            new SimpleFilter(),             /** 一个简单的过滤器 */
                            new RequestPatternFilter(),     /** 请求的模式过滤器 */
                            new UserLoginFilter()           /** 用户登录过滤器(用于限制用户的访问次数) */
                           };
    }
}
