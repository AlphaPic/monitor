package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IRequestResponseDBService;
import com.fan.dao.interfaces.baseService.mapper.IRequestResponseMapper;
import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.ResponseInfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:09:45
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:请求的数据库服务
 * @detail:
 */
@Component
public class RequestResponseDBServiceImpl implements IRequestResponseDBService {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseDBServiceImpl.class);

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<RequestInfo> getRequestInfo(String className) {
        IRequestResponseMapper mapper = sqlSession.getMapper(IRequestResponseMapper.class);
        List<RequestInfo> classInfo = null;
        try{
            classInfo = mapper.getRequestInfoByClassName(className);
        }catch (Exception e){
            logger.error("获取请求类型信息失败" + e.getMessage());
            return null;
        }
        return classInfo;
    }

    @Override
    public List<ResponseInfo> getResponseInfo(String className) {
        IRequestResponseMapper mapper = sqlSession.getMapper(IRequestResponseMapper.class);
        List<ResponseInfo> classInfo = null;
        try{
            classInfo = mapper.getResponseInfoByClassName(className);
        }catch (Exception e){
            logger.error("获取响应类型信息失败" + e.getMessage());
            return null;
        }
        return classInfo;
    }

    @Override
    public List<ClassInfo> getClassInfo(String className) {
        IRequestResponseMapper mapper = sqlSession.getMapper(IRequestResponseMapper.class);
        List<ClassInfo> classInfo = null;
        try{
            classInfo = mapper.getClassInfoListByClassName(className);
        }catch (Exception e){
            logger.error("获取类的类型信息失败" + e.getMessage());
            return null;
        }
        return classInfo;
    }

    public static void main(String[] args){

    }
}
