package com.fan.framework.annotation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2017-12-10 18:58:32
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:响应不能为空
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public @interface ResponseNotNull {
}
