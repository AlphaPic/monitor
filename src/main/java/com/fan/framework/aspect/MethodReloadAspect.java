package com.fan.framework.aspect;

import com.fan.dao.model.BaseRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:fanwenlong
 * @date:2017-12-10 20:16:28
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:把传入的请求进行重新组装
 */
@Aspect
@Order(0)
@Component
public class MethodReloadAspect {
    @Pointcut("@target(org.springframework.web.bind.annotation.RequestMapping)")
    public void monitorController(){}

    @Around(value = "monitorController() && args(request,..)")
    public Object methodReload(ProceedingJoinPoint jp, BaseRequest request){
        System.out.println("before");
        Object object = null;
        try {
            object = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after");

        return object;
    }
}
