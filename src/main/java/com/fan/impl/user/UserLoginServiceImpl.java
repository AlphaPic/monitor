package com.fan.impl.user;

import com.fan.consts.AuthEnum;
import com.fan.consts.BasicEnum;
import com.fan.consts.EncryptEnum;
import com.fan.consts.InitConfig;
import com.fan.dao.interfaces.user.IUserLoginService;
import com.fan.dao.model.AlphaResponse;
import com.fan.framework.annotation.Auth;
import com.fan.framework.annotation.MonitorController;
import com.fan.utils.*;
import com.fan.vo.request.user.*;
import com.fan.vo.response.user.UserInfoResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    /**
     * 登录
     * 1.登录首先将检查redis中有没有这个cookie，没有直接返回错误
     * 2.当cookie存在的时候，将'用户名 + 密码'进行md5的hash计算,并拿到计算的结果
     * 3.查询用户数据库，当'用户名 + 密码'进行md5计算的结果与数据库中进行匹配
     * 4.匹配成功，设置cookie的状态为登录状态，并且将cookie的过期时间刷新为30分钟之后过期
     * 5.匹配失败，返回错误
     * 6.在之后的每一次调用需要登录的接口都会刷新cookie的过期时间
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "com.fan.user.Login",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.UNCESSARY)
    @Override
    public AlphaResponse<Boolean> login(UserLoginRequest request) {
        String cookie = request.getMonitorCookie();
        String userName = request.getUserName();
        String password = request.getPassword();

        String redisValue = RedisUtils.getRedisValue(cookie);
        if(StringUtils.isEmpty(redisValue)){
            logger.error("cookie已经过期，请重新去请求cookie");
            return AlphaResponse.error("-1","cookie已经过期");
        }
        String loginInfo = userName + password;
        /** 利用用户名 + 密码计算比对数据库的hash */
        String passwordHash = EncryptUtils.generateHash(EncryptEnum.MD5,loginInfo);
        if(DBUtils.userIlleagle(userName,passwordHash)){
            logger.error("用户名或者密码错误,userName = " + userName + ",password = " + password);
            return AlphaResponse.error("-1","用户名或者密码非法");
        }
        /** 设置本cookie的redis值过期时间为30分钟 */
        String value = BasicEnum.LOGIN_STATUS.LOGIN.toString();
        RedisUtils.setStringValue(cookie,value, InitConfig.userSurviveTime);
        logger.info("用户名为" + userName + "登录成功!!!");
        return AlphaResponse.success(true);
    }

    /**
     * 获取登录的cookie
     * 登录的cookie是一个用户登录之后的一个凭证，凭借这个凭证，之后可以调用需要登录才能调用的接口，为了获取cookie，我们需要以下一些信息,这些信息可以唯一标识一个用户登录时的状况
     * 用户的ip + 用户名 + 密码 + 用户的登录方式(TOUCH,IOS,ANDROID) + 登录时间 + 一个4位随机数
     * 将上面的信息变成一个字符串，并且进行SHA-256计算
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "com.fan.user.getCookie",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.UNCESSARY)
    @Override
    public AlphaResponse<String> getLoginCookie(CookieRequest request) {
        logger.info("正在调用获取cookie的方法");
        String ip = request.getIp();
        String channel = request.getLoginChannel();

        if(InternetUtils.isIpIlleagle(ip) || InternetUtils.isChannelIlleagle(channel)){
            logger.error("用户输入的ip或者请求通道非法,ip = " + ip + ",channel = " + channel);
            return AlphaResponse.error("-1","用户输入的ip或者请求通道非法,ip = " + ip + ",channel = " + channel);
        }
        /** 获取用户名和密码 */
        String username = request.getUserName();
        String password = request.getPassword();
        if(InternetUtils.isUserNameIlleagle(username) || InternetUtils.isPasswordIlleagle(password)){
            logger.error("用户名或者密码不合法,username = " + username + ",password = " + password);
            return AlphaResponse.error("-1","用户名或者密码不合法,username = " + username + ",password = " + password);
        }
        /** 获取当前系统的时间，作为生成cookie的hash的一部分 */
        Long timeStamp = System.currentTimeMillis();
        /** 获取随机数作为末尾的四位数 */
        String randNum   = RandomUtils.getRandomNumber(4);
        String origin  = ip + username + password + channel;
        /** 生成hash值 */
        String cookie = EncryptUtils.generateHash(EncryptEnum.SHA_256,origin);
        /** 把cookie放到redis缓存,如果这个cookie在120秒中没有置为登录的状态，则将其从redis中扔掉 */
        String redisValue = BasicEnum.LOGIN_STATUS.LOGOUT.toString() + "_" + timeStamp + "_" + randNum;
        Long redisTime    = Long.valueOf(120);
        /** 转换cookie */
        String hexCookie = EncryptUtils.exchangeTentoHex(cookie);
        if(StringUtils.isEmpty(hexCookie)){
            logger.error("生成的cookie不对,cookie = " + cookie);
            return AlphaResponse.error("-1","生成的cookie不对，请重新生成");
        }
        if(RedisUtils.setStringValue(hexCookie, redisValue, redisTime) == true){
            return AlphaResponse.success(hexCookie,"生成了cookie,这个cookie将维持" + redisTime + "秒");
        } else {
            return AlphaResponse.error("-1","你所生成的cookie没有放到缓存，请稍后再试");
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
    @RequestMapping(value = "com.fan.user.cancel",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.FORCE)
    @Override
    public AlphaResponse<Boolean> cancelLogin(CancelRequest request) {
        String cookie = request.getMonitorCookie();
        String cookieValue = RedisUtils.getRedisValue(cookie);
        if(StringUtils.isEmpty(cookieValue)){
            logger.error("用户的cookie不存在");
            return AlphaResponse.error("-1","用户的cookie不存在");
        }
        if(RedisUtils.deleteRedisKey(cookie) == false){
            logger.error("删除用户的登录信息失败");
            return AlphaResponse.error("-1","退出登录失败");
        }
        return AlphaResponse.error("-1","取消登录成功");
    }

    /**
     * 获取用户登录的信息
     * 1.根据cookie来获取用户id
     * 2.根据用户的id去查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "com.fan.user.getInfo",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.FORCE)
    @Override
    public AlphaResponse<UserInfoResponseVo> getUserInfo(UserInfoRequest request) {
        AlphaResponse<UserInfoResponseVo> response = new AlphaResponse<UserInfoResponseVo>();
        UserInfoResponseVo responseVo = new UserInfoResponseVo();
        response.setDate(responseVo);

        String cookie = request.getMonitorCookie();
        if(StringUtils.isEmpty(cookie)){
            logger.error("用户的登录cookie不能为空");
            return AlphaResponse.error("-1","用户的登录cookie不能为空");
        }
        Object user = DBUtils.getUserInfo(request.getUserId());
        if(user == null){
            logger.error("用户不存在");
            return AlphaResponse.error("-1","用户不存在");
        }
        /** 拷贝用户属性 */
        BeanUtils.copyProperties(user,responseVo);
        return response;
    }
}
