package com.fan.framework.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @author:fanwenlong
 * @date:2017-12-10 16:16:23
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:控制器注解
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@Component
@RequestMapping(value = "/rest",produces = "application/json;charset=UTF-8")
public @interface MonitorController {
}
