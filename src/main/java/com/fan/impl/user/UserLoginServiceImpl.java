package com.fan.impl.user;

import com.fan.consts.AuthEnum;
import com.fan.consts.BasicEnum;
import com.fan.consts.EncryptEnum;
import com.fan.consts.InitConfig;
import com.fan.dao.interfaces.baseService.IUserCacheService;
import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.interfaces.user.IUserLoginService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.User;
import com.fan.dao.model.basicService.UserSecurity;
import com.fan.framework.annotation.Auth;
import com.fan.framework.annotation.MonitorController;
import com.fan.utils.*;
import com.fan.vo.request.user.*;
import com.fan.vo.response.user.UserInfoResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:fanwenlong
 * @date:2017-12-25 15:16:26
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录的相关接口
 */
@MonitorController
public class UserLoginServiceImpl implements IUserLoginService{
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private IUserDBService userDBService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private IUserCacheService userCacheService;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/com.fan.user.Login/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.UNCESSARY)
    @Override
    public AlphaResponse<Boolean> login(UserLoginRequest request) {
        String cookie = request.getMonitorCookie();
        String userName = request.getUserName();
        String password = request.getPassword();

        /** 1.判断用户的cookie是否具有登录权限,有的话直接返回 */
        if(userCacheService.doUserHasLoginPermit(cookie)){
            logger.error("用户已经成功登录");
            return AlphaResponse.success("1",true,"用户已经登录过了,无需重复登录","用户无需重复登录");
        }
        /** 2.假如没有登录权限，则需要校验用户是不是已经锁住了 */
        if(userCacheService.getUserLoginTimes(userName) <= 0){
            logger.error("用户的尝试已经超过了用户今天登录的上线");
            return AlphaResponse.error("-1","登录次数已经超出上线，请明天再试");
        }

        /** 3.假如传入的cookie没有登录权限，则需要进行校验用户名和密码 */
        try {
            UserSecurity userSecurity = userDBService.getUserPasswordInfo(userName);
            if(StringUtils.isEmpty(userSecurity.getUserName()) || StringUtils.isEmpty(userSecurity.getUserHash()) || userName.equals(userSecurity.getUserName()) == false) {
                logger.error("用户名和密码不正确,userNameInside = " + userSecurity.getUserName() + ",userPasswordInside = " + userSecurity.getUserHash() + ",userNameOutSide = " + userName);
                userCacheService.decreaseUserLoginTryTimes(userName);
                return AlphaResponse.error("-1","登录失败,请校验用户名和密码");
            }
            String passwordInside = userSecurity.getUserHash();
            String passwordHash = EncryptUtils.generateHash(EncryptEnum.SHA_256,password);
            if(passwordInside.equals(passwordHash)){
                userCacheService.setUserLoginPermit(cookie,userName);
                return AlphaResponse.success(true,"用户登录成功");
            }else {
                /** 尝试失败则增加失败的次数 */
                userCacheService.decreaseUserLoginTryTimes(userName);
                return AlphaResponse.error("-1", "用户名或者密码错误");
            }
        }catch (Exception e){
            logger.error("用户登录时出现异常" + e.getMessage());
            return AlphaResponse.error("-1","用户登录出现异常,请稍后再试");
        }
    }

    /**
     * 取消登录
     * 1.判断cookie是不是存在，如果不存在则不存在取消的问题
     * 2.假如存在，删除cookie在redis中的缓存
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/com.fan.user.cancel/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.FORCE)
    @Override
    public AlphaResponse<Boolean> cancelLogin(CancelRequest request) {
        String cookie = request.getAlphaCookie();
        if(userCacheService.deleteUserLoginInfo(cookie) == false){
            logger.error("用户取消登录失败");
            return AlphaResponse.error("-1","用户取消登录失败");
        }
        return AlphaResponse.success(true,"取消登录成功");
    }

    /**
     * 获取用户登录的信息
     * 1.根据cookie来获取用户id
     * 2.根据用户的id去查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/com.fan.user.getInfo/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.FORCE)
    @Override
    public AlphaResponse<User> getUserInfo(UserInfoRequest request) {
        AlphaResponse<User> response = new AlphaResponse<User>();
        User responseVo = new User();
        response.setDate(responseVo);

        String cookie = request.getAlphaCookie();
        if(StringUtils.isEmpty(cookie)){
            logger.error("用户的登录cookie不能为空");
            return AlphaResponse.error("-1","用户的登录cookie不能为空");
        }
        try {
            String userName = userCacheService.getUserNameByCookie(cookie);
            if(StringUtils.isEmpty(userName)){
                logger.error("获取的用户信息为空");
                return AlphaResponse.error("-1","没有获取到用户的信息,请重试");
            }
            responseVo = userDBService.getUserByUserName(userName);
            if (responseVo == null) {
                logger.error("用户不存在");
                return AlphaResponse.error("-1", "用户不存在");
            }
        }catch (Exception e){
            logger.error("获取用户信息出现异常" + e.getMessage());
            return AlphaResponse.error("-1","获取用户信息出现异常");
        }
        return response;
    }
}
