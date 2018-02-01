package com.fan.dao.interfaces.baseService.mapper;

import com.fan.utils.ScanClassVoUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author:fanwenlong
 * @date:2018-02-01 10:48:32
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo的扫描mapper
 * @detail:
 */
@Mapper
public interface IVoMapper {
    /************************************插入基本信息*************************************/
    @Insert("insert into classinfo (name,type,description,classname) values(#{name},#{type},#{description},#{classname})")
    Integer insertBasicVo(@Param("classname")   String classname,
                          @Param("name")        String name,
                          @Param("type")        String type,
                          @Param("description") String description);

    /************************************插入请求信息*************************************/
    @Insert("insert into requestvoinfo values(#{name},#{type},#{description},#{classname},#{demoval})")
    Integer insertRequestVo(@Param("classname")   String classname,
                            @Param("name")        String name,
                            @Param("type")        String type,
                            @Param("description") String description,
                            @Param("demoval")     String demoval);



    /************************************插入响应信息*************************************/
    @Insert("insert into responsevoinfo values(#{name},#{type},#{description},#{classname})")
    Integer insertResponseVo(@Param("classname")   String classname,
                             @Param("name")        String name,
                             @Param("type")        String type,
                             @Param("description") String description);
}
