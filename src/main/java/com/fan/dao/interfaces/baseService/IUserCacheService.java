package com.fan.dao.interfaces.baseService;

/**
 * @author:fanwenlong
 * @date:2018-01-09 11:02:12
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户相关的服务
 * @detail:
 */
public interface IUserCacheService {

    /** 判断cookie是不是具有注册权限 */
    public Boolean doUserHasRegistryPermit(String cookie);

    /** 判断用户是不是具有登录权限 */
    public Boolean doUserHasLoginPermit(String cookie);

    /** 判断cookie是不是已经过期 */
    public Boolean isCookieOverDue(String cookie);

    /** 通过cookie获取用户名称 */
    public String getUserNameByCookie(String cookie);

    /** 设置用户的登录权限 */
    public Boolean setUserLoginPermit(String cookie,String userName);

    /** 刷新用户的登录权限 */
    public Boolean refreshUserLoginPermit(String cookie);

    /** 增加用户的登录次数 */
    public Boolean decreaseUserLoginTryTimes(String userName);

    /** 获取用户的登录次数 */
    public Integer getUserLoginTimes(String userName);

    /** 判断用户是不是锁住了 */
    public Boolean isUserLoginLocked(String userName);

    /** 设置用户的登录次数 */
    public Boolean setUserLoginTimes(String userName,Integer times);

    /** 删除用户的登录信息 */
    public Boolean deleteUserLoginInfo(String cookie);
}
