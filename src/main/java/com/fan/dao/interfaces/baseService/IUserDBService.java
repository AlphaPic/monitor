package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.basicService.Address;
import com.fan.dao.model.basicService.User;
import com.fan.dao.model.basicService.UserSecurity;

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

    /**
     * 保存用户密码
     * @param security
     * @return
     */
    public Boolean saveUserPassword(UserSecurity security);

    /**
     * 更新用户密码
     * @param security
     * @return
     */
    public Boolean updateUserPassword(UserSecurity security);

    /**
     * 通过用户名称拿到用户密码信息
     * @param userName
     * @return
     */
    public UserSecurity getUserPasswordInfo(String userName);

    /**
     * 通过国家名称来获取地址
     * @param country
     * @return
     */
    public Boolean loadAddressFromDB(String country);

    /**
     * 判断国家是不是存在
     * @param country
     * @return
     */
    public Boolean countryExists(String country);

    /**
     * 判断省份是否存在
     * @param province
     * @return
     */
    public Boolean provinceExists(String province);
}
