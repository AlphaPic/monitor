package com.fan.framework.annotation;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2018-01-31 16:08:50
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:类的vo
 * @detail:
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassVo {
    String name() default "BASIC";
}
