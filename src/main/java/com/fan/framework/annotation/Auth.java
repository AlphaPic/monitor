package com.fan.framework.annotation;

import com.fan.consts.AuthEnum;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2017-12-20 21:46:09
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:登录校验的注解
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface Auth {
    AuthEnum value() default AuthEnum.FORCE;
}
