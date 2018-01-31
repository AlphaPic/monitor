package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.ResponseInfo;

import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:10:38
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:请求响应数据库服务接口
 * @detail:
 */
public interface IRequestResponseDBService {

    /**
     * 根据类名获取请求类的相关信息
     * @param className
     * @return
     */
    public List<RequestInfo> getRequestInfo(String className);

    /**
     * 根据类名获取响应类的信息
     * @param className
     * @return
     */
    public List<ResponseInfo> getResponseInfo(String className);

    /**
     * 获取非请求和响应类的信息
     * @param className
     * @return
     */
    public List<ClassInfo> getClassInfo(String className);
}
