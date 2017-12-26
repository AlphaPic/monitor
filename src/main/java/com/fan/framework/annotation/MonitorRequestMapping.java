package com.fan.framework.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2017-12-25 15:23:48
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:拓展了RequestMapping
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface MonitorRequestMapping {
    /**
     * 请求的方法
     * @return
     */
    String req_method() default "";

    /**
     * 请求的方法版本
     * @return
     */
    String req_version() default "";
}
