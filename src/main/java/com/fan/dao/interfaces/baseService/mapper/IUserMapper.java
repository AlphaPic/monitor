package com.fan.dao.interfaces.baseService.mapper;

import com.fan.dao.model.basicService.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author:fanwenlong
 * @date:2017-12-28 14:42:56
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户映射文件
 * @detail:用于用户的操作
 */
@Mapper
public interface IUserMapper {

    /** 根据userid查询用户信息 */
    @Select("SELECT * FROM USER WHERE userid = #{userId}")
    User getUserInfoByUserId(@Param("userid") Integer userId);

    /** 根据userNo查询用户信息 */
    @Select("SELECT * FROM USER WHERE userno = #{userNo}")
    User getUserInfoByUserUserNo(@Param("userno") Integer userNo);

    /** 查询userId的最大值 */
    @Select("SELECT MAX(USERID) FROM USER")
    Integer getMaxUserId();

    /** 插入用户的必传信息 */
    @Insert("INSERT INTO USER (userid,username,userno,age,sex,born,createtime,isactive) VALUES(#{userId},#{userName},#{userNo},#{age},#{sex},#{born},#{createTime},#{isActive})")
    int insertUserNessaryInfo(
                          @Param("userid")          Integer     userId,
                          @Param("username")        String      userName,
                          @Param("userno")          String      userNo,
                          @Param("age")             Integer     age,
                          @Param("sex")             String      sex,
                          @Param("born")            Date        born,
                          @Param("createtime")      Date        createTime,
                          @Param("isactive")        Integer     isActive);

    /** 插入用户的选传信息 */
    @Update("UPDATE USER SET " +
            "country    = #{country}" +
            "province   = #{province}" +
            "city       = #{city}" +
            "street     = #{street}" +
            "collage    = #{collage}" +
            "company    = #{company}" +
            "mobile     = #{mobile}" +
            "email      = #{email}" +
            "hobby      = #{hobby}" +
            "WHERE " +
            "userid = #{userId}")
    int insertUserUnnessaryInfo(@Param("userid")    Integer     userId,
                                @Param("country")   String      country,
                                @Param("province")  String      province,
                                @Param("city")      String      city,
                                @Param("street")    String      street,
                                @Param("collage")   String      collage,
                                @Param("company")   String      company,
                                @Param("mobile")    String      mobile,
                                @Param("email")     String      email,
                                @Param("hobby")     String      hobby);


    /** 获取用户的总数 */
    @Select("SELECT COUNT(*) FROM USER")
    Long getUserCount();

    /** 插入用户操作的状态 */
    @Insert("INSERT INTO USERSTATUS (userid,operationtime,activity) VALUES(#{userId},#{operationTime},#{activity})")
    int insertUserStatus(@Param("userid")           Integer     userId,
                         @Param("operationtime")    Date        operationTime,
                         @Param("activity")         String      activity);
}
