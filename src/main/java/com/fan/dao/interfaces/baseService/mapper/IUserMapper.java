package com.fan.dao.interfaces.baseService.mapper;

import com.fan.dao.model.basicService.User;
import com.fan.dao.model.basicService.UserStatus;
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

    /*****************************************************用户表相关操作***************************************************************/
    /** 根据userid查询用户信息 */
    @Select("SELECT * FROM USERINFO WHERE userid = #{userid}")
    User getUserInfoByUserId(@Param("userid") Integer userid);

    /** 根据userNo查找用户信息 */
    @Select("SELECT * FROM USERINFO WHERE username = #{username}")
    User getUserInfoByUserName(@Param("username") String username);

    /** 根据userNo查询用户信息 */
    @Select("SELECT * FROM USERINFO WHERE userno = #{userno}")
    User getUserInfoByUserUserNo(@Param("userno") String userno);

    /** 查询userId的最大值 */
    @Select("SELECT MAX(USERID) FROM USERINFO")
    Integer getMaxUserId();

    /** 插入用户的必传信息 */
    @Insert("INSERT INTO USERINFO (userid,username,userno,age,sex,born,createtime,isactive) " +
            "VALUES (#{userid},#{username},#{userno},#{age},#{sex},#{born},#{createtime},#{isactive})")
    int insertUserNessaryInfo(
                          @Param("userid")          Integer     userid,
                          @Param("username")        String      username,
                          @Param("userno")          String      userno,
                          @Param("age")             Integer     age,
                          @Param("sex")             String      sex,
                          @Param("born")            Date        born,
                          @Param("createtime")      Date        createtime,
                          @Param("isactive")        Integer     isactive);

    /** 插入用户的选传信息 */
    @Update("UPDATE USERINFO SET " +
            "country    = #{country}," +
            "province   = #{province}," +
            "city       = #{city}," +
            "street     = #{street}," +
            "collage    = #{collage}," +
            "company    = #{company}," +
            "mobile     = #{mobile}," +
            "email      = #{email}," +
            "hobby      = #{hobby}" +
            "WHERE " +
            "userid = #{userid}")
    int insertUserUnnessaryInfo(@Param("userid")    Integer     userid,
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
    @Select("SELECT COUNT(*) FROM USERINFO")
    Long getUserCount();

    /** 激活用户 */
    @Update("UPDATE USERINFO SET isactive = 1 WHERE userid = #{userid}")
    int activeUser(@Param("userid") Integer userid);

    /*****************************************************用户执行历史表相关操作***********************************************************/
    /** 插入用户操作的状态 */
    @Insert("INSERT INTO USEROPERATION (userid,operationtime,activity) VALUES(#{userid},#{operationtime},#{activity})")
    int insertUserOperation(@Param("userid")           Integer     userid,
                            @Param("operationtime")    Date        operationtime,
                            @Param("activity")         String      activity);

    /*****************************************************用户状态表相关操作*************************************************************/
    /** 生成用户激活码 */
    @Insert("INSERT INTO USERSTATUS(userid,activecode,registrychannel) VALUES(#{userid},#{activecode},#{registrychannel})")
    int insertUserActiveCode(@Param("userid")          Integer     userid,
                             @Param("activecode")      String      activecode,
                             @Param("registrychannel") String      registrychannel);

    /** 获取用户的状态 */
    @Select("SELECT * FROM USERSTATUS WHERE USERID = #{userid}")
    UserStatus getUserStatus(@Param("userid") Integer userid);

    /*****************************************************用户安全表相关操作*************************************************************/
    /** 保存密码 */
    @Insert("INSERT INFO USERSECURITY (userid,userhash) values (#{userid},#{userhash})")
    int savePasswordToUserSecurity(@Param("userid")     Integer userid,
                                   @Param("userhash")   String userhash);

    /** 更新密码 */
    @Update("UPDATE USERSECURITY SET userhash = #{userhash} where userid = #{userid}")
    int updatePasswordToUserSecurity(@Param("userid")   Integer userid,
                                     @Param("userhash") String userhash);
}
