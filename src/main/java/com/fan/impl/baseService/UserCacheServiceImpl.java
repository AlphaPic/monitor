package com.fan.impl.baseService;

import com.fan.consts.UserConfig;
import com.fan.dao.interfaces.baseService.IUserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

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
     * @param cookie
     * @return
     */
    @Override
    public Boolean doUserHasRegistryPermit(String cookie) {
        return false;
    }

    /**
     * 判断用户是不是具有登录权限
     * @param cookie
     * @return
     */
    @Override
    public Boolean doUserHasLoginPermit(String cookie) {
        String loginPermit = UserConfig.REDIS_LOGIN_PERMIT_COOKIE + cookie;
        try{
            String userName = redisTemplate.opsForValue().get(loginPermit);
            if(StringUtils.isEmpty(userName)){
                logger.error("查不到这个cookie");
                return false;
            }
        }catch (Exception e){
            logger.error("用户获取登录权限时出现异常" + e.getMessage());
            return false;
        }
        return true;
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


    /**
     * 通过cookie查找注册用户的名称
     * @param cookie
     * @return
     */
    @Override
    public String getUserNameByCookie(String cookie) {
        String loginPermit = UserConfig.REDIS_LOGIN_PERMIT_COOKIE + cookie;
        try{
            String userName = redisTemplate.opsForValue().get(loginPermit);
            return userName;
        }catch (Exception e){
            logger.error("获取用户的登录信息出现异常" + e.getMessage());
            return null;
        }
    }

    /**
     * 设置用户的登录权限
     * @param cookie
     * @return
     */
    @Override
    public Boolean setUserLoginPermit(String cookie,String userName) {
        if(StringUtils.isEmpty(cookie) || StringUtils.isEmpty(userName)){
            logger.error("用户登录的信息不能为空,cookie = " + cookie + ",userName = " + userName);
            return false;
        }
        String loginPermit = UserConfig.REDIS_LOGIN_PERMIT_COOKIE + cookie;
        try{
            redisTemplate.opsForValue().set(loginPermit,userName,UserConfig.REDIS_LOGIN_PERMIT_TIME);
        }catch (Exception e){
            logger.error("设置用户登录权限时出现异常");
            return false;
        }
        return true;
    }

    /**
     * 刷新用户的登录权限
     * @param cookie
     * @return
     */
    @Override
    public Boolean refreshUserLoginPermit(String cookie) {
        if(StringUtils.isEmpty(cookie)){
            logger.error("用户的cookie不能为空");
            return false;
        }
        String loginPermit = UserConfig.REDIS_LOGIN_PERMIT_COOKIE + cookie;
        try{
            String userName = redisTemplate.opsForValue().get(loginPermit);
            if(StringUtils.isEmpty(userName)){
                logger.error("用户的的登录权限已经过期,无法刷新用户的登录权限");
                return false;
            }
            /** 重设用户的登录权限 */
            setUserLoginPermit(cookie,userName);
        }catch (Exception e){
            logger.error("刷新用户的登录权限时失败");
            return false;
        }
        return true;
    }

    /**
     * 减少用户的登录尝试次数
     * @param userName
     * @return
     */
    @Override
    public Boolean decreaseUserLoginTryTimes(String userName) {
        if(StringUtils.isEmpty(userName)){
            logger.error("用户的名称或者登录次数为空,userName = " + userName);
            return false;
        }
        try{
            Integer curTimes = getUserLoginTimes(userName);
            if(curTimes <= 0){
                logger.error("用户已经超出了登录次数的上线，请稍后再试");
                return false;
            }
            String loginTimeKey = UserConfig.REDIS_LOGIN_TRY_TIMES + userName;
            redisTemplate.opsForValue().increment(loginTimeKey, -1);
        }catch (Exception e){
            logger.error("在减少用户次数的时候出现异常" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取用户的登录次数
     * @param userName
     * @return
     */
    @Override
    public Integer getUserLoginTimes(String userName) {
        if(StringUtils.isEmpty(userName)){
            logger.error("用户的信息为空");
            return -1;
        }
        try{
            String loginTimeKey = UserConfig.REDIS_LOGIN_TRY_TIMES + userName;
            Integer curTimes = Integer.valueOf(redisTemplate.opsForValue().get(loginTimeKey) == null ? "0" : redisTemplate.opsForValue().get(loginTimeKey));
            return curTimes;
        }catch (Exception e){
            logger.error("获取用户登录次数时出现异常" + e.getMessage());
            return -1;
        }
    }

    /**
     * 判断用户是不是登录时候锁住了
     * @param userName
     * @return
     */
    @Override
    public Boolean isUserLoginLocked(String userName) {
        if(StringUtils.isEmpty(userName)){
            logger.error("用户名称不能为空");
            return true;
        }
        try {
            String userLock = UserConfig.REDIS_LOGIN_LOCK + userName;
            String isLocked = redisTemplate.opsForValue().get(userLock);
            if(StringUtils.isEmpty(isLocked) || "false".equals(isLocked)){
                logger.error("用户处于锁定状态");
                return true;
            }
        }catch (Exception e){
            logger.error("获取用户锁定状态时出现异常");
            return true;
        }
        return false;
    }

    /**
     * 设置用户的登陆次数
     * @param userName
     * @param times
     * @return
     */
    @Override
    public Boolean setUserLoginTimes(String userName, Integer times) {
        if(times == null || times <= 0 || StringUtils.isEmpty(userName)){
            logger.error("用户的名称或者登录次数为空,userName = " + userName + ",times = " + times);
            return false;
        }
        try{
            String loginTimes = UserConfig.REDIS_LOGIN_TRY_TIMES + userName;
            redisTemplate.opsForValue().set(loginTimes, String.valueOf(times));
        }catch (Exception e){
            logger.error("设置次数的时候出现异常" + e.getMessage());
        }
        return true;
    }

    /**
     * 删除用户的登录信息
     * @param cookie
     * @return
     */
    @Override
    public Boolean deleteUserLoginInfo(String cookie) {
        if(StringUtils.isEmpty(cookie)){
            logger.error("用户的cookie为空");
            return false;
        }
        String loginCookie = UserConfig.REDIS_LOGIN_PERMIT_COOKIE + cookie;
        String userName = null;
        try{
            userName = redisTemplate.opsForValue().get(loginCookie);
            redisTemplate.delete(loginCookie);
            /** 把用户的cookie的登录次数设置为默认的次数 */
            if(setUserLoginTimes(userName,UserConfig.REDIS_LOGIN_TIMES) == false){
                redisTemplate.opsForValue().set(loginCookie,userName,UserConfig.REDIS_LOGIN_PERMIT_TIME, TimeUnit.SECONDS);
                return false;
            }
        }catch (Exception e){
            logger.error("删除用户登录信息失败,重新设置用户的登录状态");
            if(StringUtils.isEmpty(userName) == false) {
                redisTemplate.opsForValue().set(loginCookie,userName,UserConfig.REDIS_LOGIN_PERMIT_TIME, TimeUnit.SECONDS);
            }
            return false;
        }
        return true;
    }
}
