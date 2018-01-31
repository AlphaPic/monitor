package com.fan.dao.interfaces.monitor;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.ClassVoSearchRequest;
import com.fan.dao.model.request.RequestVoSearchRequest;
import com.fan.dao.model.request.ResponseVoSearchRequest;
import com.fan.dao.model.response.ClassVoSearchResponse;
import com.fan.dao.model.response.RequestVoSearchResponse;
import com.fan.dao.model.response.ResponseVoSearchResponse;

/**
 * @author:fanwenlong
 * @date:2018-01-25 16:40:35
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户的vo执行服务
 * @detail:
 */
public interface IUserVoExecutionService {

    /**
     * 获取请求对象的值
     * @param request
     * @return
     */
    public AlphaResponse<RequestVoSearchResponse> getRequestVoClassInfo(RequestVoSearchRequest request);

    /**
     * 获取响应对象的值
     * @param request
     * @return
     */
    public AlphaResponse<ResponseVoSearchResponse> getRequestVoClassInfo(ResponseVoSearchRequest request);

    /**
     * 根据类名来获取类的信息
     * @param request
     * @return
     */
    public AlphaResponse<ClassVoSearchResponse> getClassVoClassInfo(ClassVoSearchRequest request);
}
