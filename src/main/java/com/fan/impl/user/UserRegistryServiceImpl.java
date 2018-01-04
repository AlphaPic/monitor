package com.fan.impl.user;

import com.fan.consts.BasicEnum;
import com.fan.consts.UserConfig;
import com.fan.dao.interfaces.baseService.IMailService;
import com.fan.dao.interfaces.user.IUserRegistryService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.ActivateUserRequest;
import com.fan.dao.model.request.RegistryCodeRequest;
import com.fan.dao.model.request.RegistryMediaRequest;
import com.fan.dao.model.request.RegistryRequest;
import com.fan.dao.model.response.RegistryMediaResponse;
import com.fan.framework.annotation.MonitorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:fanwenlong
 * @date:2018-01-04 15:03:56
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户注册类
 * @detail:
 */
@MonitorController
public class UserRegistryServiceImpl implements IUserRegistryService{
    private static final Logger logger = LoggerFactory.getLogger(UserRegistryServiceImpl.class);

    @Autowired
    private IMailService mailService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 获取验证码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.getAuthCode/1.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> getRegistryCode(RegistryMediaRequest request) {
        AlphaResponse<Boolean> response = new AlphaResponse<Boolean>();
        String registryType = request.getRegistryType();            /** 注册类型 */
        String email        = request.getMail();                    /** email */
        String mobile       = request.getMobile();                  /** 手机号 */

        BasicEnum.REGISTRY_TYPE type = BasicEnum.REGISTRY_TYPE.getRegistryTypeSupport(registryType);
        switch (type){
            case MAIL:
                if(BasicEnum.MAIL_SUPPORT.isMailSupport(email) == false){
                    return AlphaResponse.error("-1","不支持的邮件类型");
                }
                /** 判断或者注册验证码的账户是不是已经存在 */
                if(mailService.isMailAccountExists(email) == true){
                    return AlphaResponse.error("-1","邮箱账户已存在");
                }
                /** 判断是不是可以发送验证码 */
                String emailLockKey = UserConfig.REGISTRY_EMAIL_ACCOUNT_LOCK + email;
                try{
                    String emailLockValue = redisTemplate.opsForValue().get(emailLockKey);
                    if(false == Boolean.valueOf(emailLockValue)){
                        logger.error("您今天注册太过于频繁了，请明天再试");
                    }
                }catch (Exception e){
                    logger.error("无法获取用户是否能够继续注册的信息，");
                }
                /** 发送验证码 */
                break;
            case MOBILE:
                return AlphaResponse.error("-1","抱歉,手机注册还未开发");
            default:
                return AlphaResponse.error("-1","不支持的注册类型");
        }

        return response;
    }

    /**
     * 校验验证码的正确性
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.verifyRegistryCode/1.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> verifyRegistryCode(RegistryCodeRequest request) {
        return null;
    }

    /**
     * 注册用户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.registry/1.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> registry(RegistryRequest request) {
        return null;
    }

    /**
     * 激活用户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.activateUser/1.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> activateUser(ActivateUserRequest request) {
        return null;
    }
}
