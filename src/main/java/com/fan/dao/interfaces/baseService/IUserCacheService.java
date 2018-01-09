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

    /** 通过cookie获取注册者名称 */
    public String getUserNameByCookie(String cookie);

    /**  */
}
