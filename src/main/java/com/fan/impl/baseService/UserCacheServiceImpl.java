package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IUserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author:fanwenlong
 * @date:2018-01-09 11:06:24
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:与缓存相关的用户服务
 * @detail:
 */
@Component
public class UserCacheServiceImpl implements IUserCacheService {
    private static final Logger logger = LoggerFactory.getLogger(UserCacheServiceImpl.class);
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 判断用户是否拥有注册权限
     * @param userName
     * @return
     */
    @Override
    public Boolean doUserHasRegistryPermit(String userName) {
        return null;
    }

    /**
     * 判断用户是不是具有登录权限
     * @param cookie
     * @return
     */
    @Override
    public Boolean doUserHasLoginPermit(String cookie) {
        return null;
    }

    /**
     * 判断用户的cookie是否过期
     * @param cookie
     * @return
     */
    @Override
    public Boolean isCookieOverDue(String cookie) {
        return null;
    }

    @Override
    public String getUserNameByCookie(String cookie) {
        return null;
    }


}
