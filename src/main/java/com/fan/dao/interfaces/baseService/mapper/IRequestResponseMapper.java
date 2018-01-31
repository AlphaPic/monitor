package com.fan.dao.interfaces.baseService.mapper;

import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.ResponseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:15:26
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:请求响应映射文件
 * @detail:
 */
@Mapper
public interface IRequestResponseMapper {

    /** 通过请求类名称获取类信息 */
    @Select("select * from requestvoinfo where classname = #{classname}")
    List<RequestInfo> getRequestInfoByClassName(@Param("classname") String className);

    /** 通过响应类名称获取类信息 */
    @Select("select * from responsevoinfo where classname = #{classname}")
    List<ResponseInfo> getResponseInfoByClassName(@Param("classname") String className);

    /** 通过类名称获取信息 */
    @Select("select * from classinfo where classname = #{classname}")
    List<ClassInfo> getClassInfoListByClassName(@Param("classname") String className);
}
