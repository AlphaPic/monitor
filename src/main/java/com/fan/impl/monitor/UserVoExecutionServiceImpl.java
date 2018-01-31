package com.fan.impl.monitor;

import com.fan.dao.interfaces.baseService.IRequestResponseDBService;
import com.fan.dao.interfaces.monitor.IUserVoExecutionService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.ResponseInfo;
import com.fan.dao.model.request.ClassVoSearchRequest;
import com.fan.dao.model.request.RequestVoSearchRequest;
import com.fan.dao.model.request.ResponseVoSearchRequest;
import com.fan.dao.model.response.ClassVoSearchResponse;
import com.fan.dao.model.response.RequestVoSearchResponse;
import com.fan.dao.model.response.ResponseVoSearchResponse;
import com.fan.framework.annotation.MonitorController;
import com.fan.impl.baseService.RequestResponseDBServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 16:40:06
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:
 * @detail:
 */
@MonitorController
public class UserVoExecutionServiceImpl implements IUserVoExecutionService {
    private static final Logger logger = LoggerFactory.getLogger(UserVoExecutionServiceImpl.class);

    @Autowired
    private RequestResponseDBServiceImpl requestResponseDBService;

    /**
     * 根据类名获取类的信息
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/api.com.vo.getRequestInfo/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    public AlphaResponse<RequestVoSearchResponse> getRequestVoClassInfo(RequestVoSearchRequest request) {
        AlphaResponse<RequestVoSearchResponse> response = new AlphaResponse<RequestVoSearchResponse>();
        RequestVoSearchResponse searchResponse = new RequestVoSearchResponse();
        response.setDate(searchResponse);

        String className = request.getClassName();

        if(StringUtils.isEmpty(className)){
            return AlphaResponse.error("-1","类名不能为空");
        }

        List<RequestInfo> requestInfoList = requestResponseDBService.getRequestInfo(className);
        if(requestInfoList == null || requestInfoList.isEmpty()){
            return AlphaResponse.error("-1","返回的结果为空");
        }

        searchResponse.setVoTypeInfo("request");
        searchResponse.setRequestInfoList(requestInfoList);

        return response;
    }

    /**
     * 根据类名获取响应信息
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/api.com.vo.getResponseInfo/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    public AlphaResponse<ResponseVoSearchResponse> getRequestVoClassInfo(ResponseVoSearchRequest request) {
        AlphaResponse<ResponseVoSearchResponse> response = new AlphaResponse<ResponseVoSearchResponse>();
        ResponseVoSearchResponse searchResponse = new ResponseVoSearchResponse();
        response.setDate(searchResponse);

        String className = request.getClassName();

        if(StringUtils.isEmpty(className)){
            return AlphaResponse.error("-1","类名不能为空");
        }

        List<ResponseInfo> responseInfoList = requestResponseDBService.getResponseInfo(className);
        if(responseInfoList == null || responseInfoList.isEmpty()){
            return AlphaResponse.error("-1","返回的结果为空");
        }

        searchResponse.setVoTypeInfo("response");
        searchResponse.setResponseInfoList(responseInfoList);

        return response;
    }


    /**
     * 获取一般类的信息
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/api.com.vo.getBasicClassInfo/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    public AlphaResponse<ClassVoSearchResponse> getClassVoClassInfo(ClassVoSearchRequest request) {
        AlphaResponse<ClassVoSearchResponse> response = new AlphaResponse<ClassVoSearchResponse>();
        ClassVoSearchResponse searchResponse = new ClassVoSearchResponse();
        response.setDate(searchResponse);

        String className = request.getClassName();

        if(StringUtils.isEmpty(className)){
            return AlphaResponse.error("-1","类名不能为空");
        }

        List<ClassInfo> classInfoList = requestResponseDBService.getClassInfo(className);

        if(classInfoList == null || classInfoList.isEmpty()){
            return AlphaResponse.error("-1","返回的结果为空");
        }
        searchResponse.setVoTypeInfo("basicClass");
        searchResponse.setClassInfoList(classInfoList);

        return response;
    }
}
