package com.fan.impl.monitor;

import com.fan.dao.interfaces.baseService.IRequestResponseDBService;
import com.fan.dao.interfaces.monitor.IBasicDBService;
import com.fan.dao.interfaces.monitor.IUserVoExecutionService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.VoInfo;
import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.ResponseInfo;
import com.fan.dao.model.request.ClassVoSearchRequest;
import com.fan.dao.model.request.RefreshVODBRequest;
import com.fan.dao.model.request.RequestVoSearchRequest;
import com.fan.dao.model.request.ResponseVoSearchRequest;
import com.fan.dao.model.response.ClassVoSearchResponse;
import com.fan.dao.model.response.RefreshVODBResponse;
import com.fan.dao.model.response.RequestVoSearchResponse;
import com.fan.dao.model.response.ResponseVoSearchResponse;
import com.fan.framework.annotation.MonitorController;
import com.fan.impl.baseService.RequestResponseDBServiceImpl;
import com.fan.impl.baseService.VoDBServiceImpl;
import com.fan.utils.ScanClassVoUtils;
import org.apache.tomcat.jni.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.management.FileSystem;

import javax.naming.Context;
import java.io.*;
import java.util.Iterator;
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
public class UserVoExecutionServiceImpl implements IUserVoExecutionService,IBasicDBService{
    private static final Logger logger = LoggerFactory.getLogger(UserVoExecutionServiceImpl.class);

    @Autowired
    private RequestResponseDBServiceImpl requestResponseDBService;

    @Autowired
    private VoDBServiceImpl voDBService;

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

    /**
     * 刷新数据库中的vo服务
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/api.com.vo.refreshDBVoInfo/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    public AlphaResponse<RefreshVODBResponse> refreshDBVoInfo(RefreshVODBRequest request) {
        AlphaResponse<RefreshVODBResponse> response = new AlphaResponse<RefreshVODBResponse>();
        RefreshVODBResponse vodbResponse = new RefreshVODBResponse();
        response.setDate(vodbResponse);

        Boolean force = request.getForceRefresh();
        Integer type  = request.getVoType();

        if(force == false){

        }else{
            /** 强制扫描并且更新数据库 */
            switch (type){
                case 1:
                case 2:
                case 3:
                    List<ScanClassVoUtils.InsertVo> list = ScanClassVoUtils.scanAllVo(type,true);
                    if(writeInToDB(list) == false){
                        return AlphaResponse.error("-1","写入数据库失败");
                    }
                    vodbResponse.setRefreshTypeAmount(1);
                    break;
                case 4:
                    Integer count = 0;
                    Integer listType = type - 1;
                    while (listType > 0){
                        List<ScanClassVoUtils.InsertVo> list1 = ScanClassVoUtils.scanAllVo(listType,true);
                        if(writeInToDB(list1) == false){
                            vodbResponse.setRefreshTypeAmount(count);
                            response.setMessage("已经写入了" + count + "个类型的数据");
                            return response;
                        }
                        count++;
                        listType--;
                    }
                    break;
                default:
                    return AlphaResponse.error("-1","不支持的写入类型" + type);
            }

            response.setCode("1");
            response.setMessage("写入成功");
        }
        return response;
    }

    /**
     * 把数据写入数据库
     * @param list
     * @return
     */
    private boolean writeInToDB(List<ScanClassVoUtils.InsertVo> list){
        if(list == null || list.isEmpty()){
            return false;
        }
        /** 遍历 */
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            ScanClassVoUtils.InsertVo insertVo = (ScanClassVoUtils.InsertVo) iterator.next();
            String classname  = insertVo.getClassName();
            Integer classtype = insertVo.getClassType();
            List<ScanClassVoUtils.TypeVo> memberList = insertVo.getList();
            if(memberList == null || memberList.isEmpty()){
                continue;
            }
            Iterator iterator1 = memberList.iterator();
            while (iterator1.hasNext()){
                ScanClassVoUtils.TypeVo typeVo = (ScanClassVoUtils.TypeVo) iterator1.next();
                VoInfo voInfo = new VoInfo();
                voInfo.setClasstype(classtype);
                voInfo.setClassname(classname);
                voInfo.setMemberName(typeVo.getName());
                voInfo.setMemberType(typeVo.getType());
                voInfo.setDescription(typeVo.getComment());
                voInfo.setDemoval(typeVo.getDemoVal());

                /** 保存 */
                Boolean res = voDBService.InsertVoInfoToDb(voInfo);
                if(res == false){
                    return false;
                }
            }
        }

        return true;
    }
}























