package com.fan.framework.config;

import com.fan.framework.aspect.MethodReloadAspect;
import com.fan.impl.monitor.MessageQueueStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author:fanwenlong
 * @date:2017-12-02 12:00:15
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:Spring MVC上下文配置的相关类
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {MethodReloadAspect.class,MessageQueueStatusImpl.class},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)
        })
public class RootConfig {
}
