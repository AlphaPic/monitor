package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.basicService.User;

/**
 * @author:fanwenlong
 * @date:2017-12-27 14:49:19
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户相关的数据库接口
 * @detail:提供用户表的基础服务，之后会被拆到另外一个工程去,现在就这么写着
 */
public interface IUserDBService {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getUserByUserId(Integer userId);

    /**
     * 通过名称获取用户
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName);

    /**
     * 插入用户必传的信息
     * @return
     */
    public Boolean insertUserNessaryInfo(User user);

    /**
     * 插入用户非必传的信息
     * @return
     */
    public Boolean insertUserAllInfo(User user);
}
