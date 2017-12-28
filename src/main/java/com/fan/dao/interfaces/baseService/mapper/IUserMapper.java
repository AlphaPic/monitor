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
    @Select("SELECT * FROM USER WHERE id = #{id}")
    User getUserInfoByUserId(@Param("id") Integer id);

    @Insert("INSERT INTO USER (id,userId,userName,createTime,lastLoginTime,mobile,email,permit,status) VALUES(#{id},#{userId},#{userName},#{createTime},#{lastLoginTime},#{mobile},#{email},#{permit},#{status})")
    int insertUser(@Param("id") Integer id,
                          @Param("userId")Integer userId,
                          @Param("userName")String userName,
                          @Param("createTime")Date createTime,
                          @Param("lastLoginTime")Date lastLoginTime,
                          @Param("mobile")String mobile,
                          @Param("email")String email,
                          @Param("permit")String permit,
                          @Param("status")String status);
}
