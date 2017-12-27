package com.fan.framework.config;

import com.fan.consts.InitConfig;
import com.fan.framework.interceptor.LoginInterceptor;
import com.fan.framework.interceptor.SignatureInterceptor;
import com.fan.impl.BaseController;
import com.fan.impl.baseService.UserDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author:fanwenlong
 * @date:2017-12-02 11:46:50
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:架构的配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {BaseController.class,LoginInterceptor.class, UserDBServiceImpl.class})
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private SignatureInterceptor signatureInterceptor;
    /**
     * 配置JSP视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(InitConfig.JspResolverPrefix);
        resolver.setSuffix(InitConfig.JspResolverSuffix);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        /** 添加拦截器，并且过滤所有的请求 */
        registry.addInterceptor(loginInterceptor).addPathPatterns(InitConfig.LoginInterceptorPattern_0);
        registry.addInterceptor(signatureInterceptor).addPathPatterns(InitConfig.SigantureInterceptorPattern);
    }

    /**
     * 配置静态资源的处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
}
