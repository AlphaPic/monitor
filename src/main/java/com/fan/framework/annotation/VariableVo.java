package com.fan.framework.annotation;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2018-01-31 16:28:48
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:属性vo
 * @detail:
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VariableVo {
    String comment() default "不能为空";

    String demoVal() default "undefined";
}
