package com.fan.framework.interceptor;

import com.fan.consts.EncryptEnum;
import com.fan.consts.InitConfig;
import com.fan.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author:fanwenlong
 * @date:2017-12-26 20:59:40
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:签名拦截器
 * @detail:这个拦截器主要用来查看请求的完整性
 */
@Component
public class SignatureInterceptor implements HandlerInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(SignatureInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(InitConfig.INTERCEPTOR_SIGNATURE_BUTTON == false){
            return true;
        }
        if(isDebug(request) == true){
            return true;
        }
        if(signatureCheck(request) == true){
            return true;
        }
        return true;
    }

    /**
     * 判断是否是debug模式
     * @param request
     * @return
     */
    private Boolean isDebug(HttpServletRequest request){
        /** 先判断是不是打开开关 */
        if(InitConfig.DEBUG_MODE == false){
            return false;
        }
        /** 打开的情况下比对debugSign的值是不是一样 */
        String debugSign = request.getHeader("debugSign");
        if(StringUtils.isEmpty(debugSign)){
            return false;
        }
        if(InitConfig.SIGNATURE.equalsIgnoreCase(debugSign) == false){
            return false;
        }
        return true;
    }

    /**
     * 校验签名
     * @param request
     * @return
     */
    private Boolean signatureCheck(HttpServletRequest request){
        Map<String, ?> params = request.getParameterMap();
        params.remove("debugSign");
        params.remove("md5Key");

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String origin = "";
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = (String) params.get(key);
            origin += (key + "=" + value + "&");
        }
        origin += InitConfig.MD5_SALT;

        String md5_expect = EncryptUtils.generateHash(EncryptEnum.MD5,origin);
        String md5_origin = request.getParameter("md5Key");

        if(md5_expect.equals(md5_origin) == false){
            logger.error("MD5的签名校验不通过,expect = " + md5_expect);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
