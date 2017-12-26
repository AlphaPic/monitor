package com.fan.framework.annotation;

import com.fan.consts.AuthEnum;

/**
 * @author:fanwenlong
 * @date:2017-12-20 21:46:09
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:登录校验的注解
 */
public @interface Auth {
    AuthEnum value() default AuthEnum.FORCE;
}
