package com.fan.impl.user;

import com.fan.consts.BasicEnum;
import com.fan.consts.InitConfig;
import com.fan.consts.UserConfig;
import com.fan.dao.interfaces.baseService.IMailService;
import com.fan.dao.interfaces.user.IUserRegistryService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.AlphaMessageInfo;
import com.fan.dao.model.basicService.User;
import com.fan.dao.model.request.ActivateUserRequest;
import com.fan.dao.model.request.RegistryCodeRequest;
import com.fan.dao.model.request.RegistryMediaRequest;
import com.fan.dao.model.request.RegistryRequest;
import com.fan.dao.model.response.RegistryMediaResponse;
import com.fan.framework.annotation.MonitorController;
import com.fan.impl.baseService.UserDBServiceImpl;
import com.fan.utils.RandomUtils;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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

    @Autowired
    private UserDBServiceImpl userDBService;

    /**
     * 获取验证码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.getAuthCode/1.0.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<RegistryMediaResponse> getRegistryCode(RegistryMediaRequest request) {
        AlphaResponse<RegistryMediaResponse> response = new AlphaResponse<RegistryMediaResponse>();
        RegistryMediaResponse responseVo = new RegistryMediaResponse();
        response.setDate(responseVo);
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
                String emailLockKey = UserConfig.REDIS_REGISTRY_ACCOUNT_LOCK + email;
                try{
                    String emailLockValue = redisTemplate.opsForValue().get(emailLockKey);
                    if(StringUtils.isEmpty(emailLockValue) == false && true == Boolean.valueOf(emailLockValue)){
                        logger.error("您注册太过频繁，请稍后再试" + emailLockValue);
                        return AlphaResponse.error("-1","您注册太过频繁，请稍后再试");
                    }
                }catch (Exception e){
                    logger.error("无法获取用户是否能够继续注册的信息，请稍后再试" + e.getMessage());
                    return AlphaResponse.error("-1","获取注册账户信息失败,请重试");
                }
                /** 发送验证码 */
                AlphaMessageInfo messageInfo = new AlphaMessageInfo();
                /** 暂时将发送邮件的服务器设置为QQ服务器-_-! */
                messageInfo.setFrom(InitConfig.MAIL_QQ_SMTP_USERNAME);
                messageInfo.setMultiSender(false);
                messageInfo.setSingleDest(email);
                /** 获取随机的8位数字 */
                String authCode = RandomUtils.getRandomNumber(8);
                messageInfo.setText(authCode);
                try {
                    mailService.sendASingleMail(messageInfo);
                }catch (Exception e){
                    logger.error("发送邮件失败" + e.getMessage());
                    return AlphaResponse.error("-1","发送邮件失败，请稍后再试");
                }
                String alphaCookie = RandomUtils.getAlphaCookie();
                /** 获取验证码成功
                 *  1.将验证码放置到redis缓存之中,并保存60S
                 *  2.设置当前邮件地址锁住60s */
                String authCodeSurviveInfo = UserConfig.REDIS_REGISTRY_EMAIL_ACCOUNT_AUTHCODE + alphaCookie;
                try{
                    redisTemplate.opsForValue().set(authCodeSurviveInfo,authCode,UserConfig.REDIS_REGISTRY_EMAIL_AUTH_TIMEOUT, TimeUnit.SECONDS);   /** 验证码过期时间 */
                    redisTemplate.opsForValue().set(emailLockKey,"true",UserConfig.REDIS_REGISTRY_EMAIL_AUTH_TIMEOUT, TimeUnit.SECONDS);           /** email锁住的时间 */
                    /** cookie和邮箱之间必须建立联系，这样就可以在注册的时候直接拿到email的值 */
                    String cookieAccount = UserConfig.REDIS_REGISTRY_ACCOUNT_BIND_COOKIE + alphaCookie;
                    redisTemplate.opsForValue().set(cookieAccount,email,UserConfig.REDIS_REGISTRY_EMAIL_AUTH_TIMEOUT,TimeUnit.SECONDS);
                }catch (Exception e){
                    logger.error("设置验证码出错" + e.getMessage());
                    return AlphaResponse.error("-1","设置验证码出错,请重新获取邮件");
                }
                responseVo.setSuccess(true);
                responseVo.setAlphaCookie(alphaCookie);
                response.setMessage("邮件发送成功");
                break;
            case MOBILE:
                return AlphaResponse.error("-1","抱歉,手机注册还未开发");
            default:
                return AlphaResponse.error("-1","不支持的注册类型");
        }
        return response;
    }


    /**
     * 校验验证码
     * 校验验证码为使得cookie获取注册权限的过程，当用户的cookie被成功验证之后，就可以继续往下面走注册流程了
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.verifyRegistryCode/1.0.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> verifyRegistryCode(RegistryCodeRequest request) {
        String cookie   = request.getAlphaCookie();
        String authCode = request.getAuthCode();
        AlphaResponse<Boolean> response = new AlphaResponse<Boolean>();

        String redisCookieKey = UserConfig.REDIS_REGISTRY_EMAIL_ACCOUNT_AUTHCODE + cookie;
        try{
            String cookieValue = redisTemplate.opsForValue().get(redisCookieKey);
            /** 必须获取到账户的信息，获取不到则重新获取 */
            String cookieAccount = UserConfig.REDIS_REGISTRY_ACCOUNT_BIND_COOKIE + cookie;
            String accountName  = redisTemplate.opsForValue().get(cookieAccount);
            if(StringUtils.isEmpty(cookieValue) || StringUtils.isEmpty(accountName)){
                logger.error("找不到cookie相关的数据");
                response.setDate(false);
                response.setCode("-1");
                response.setErrorMessage("验证码已过期");
                return response;
            }
            int index = cookieValue.lastIndexOf('_');
            String authTemp = cookieValue.substring(index + 1);
            if(authTemp != null && authTemp.equals(authCode) == false){
                response.setDate(false);
                response.setErrorMessage("验证码错误");
                response.setCode("-2");
                return response;
            }



            /** 运行到这里来了，表示已经验证成功，该cookie取得了注册权限 */
            String loadPermit = UserConfig.REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT + cookie;

            redisTemplate.opsForValue().set(loadPermit,"true",UserConfig.REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT_TIME,TimeUnit.SECONDS);
            /** 在这里必须对email和cookie之间绑定的进行重新赋值，时间为300s */
            redisTemplate.opsForValue().set(cookieAccount,accountName,UserConfig.REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT_TIME,TimeUnit.SECONDS);
            response.setDate(true);
            response.setMessage("验证成功,可以去注册用户了");
        }catch (Exception e){
            logger.error("获取redis数据出错");
            return AlphaResponse.error("-1","内部操作异常,请稍后再试");
        }
        return response;
    }

    /**
     * 注册用户,用于必须具有注册权限
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.registry/1.0.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> registry(RegistryRequest request) {
        AlphaResponse<Boolean> response = new AlphaResponse<Boolean>();
        String alphaCookie = request.getAlphaCookie();
        /** 验证cookie是不是拥有注册的权限，没有的话，则继续上一步骤去获取验证码并且验证 */
        String registryPermitKey = UserConfig.REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT + alphaCookie;
        try{
            String registryPermitValue = redisTemplate.opsForValue().get(registryPermitKey);
            if(StringUtils.isEmpty(registryPermitKey) || "true".equals(registryPermitKey) != true){
                logger.error("用户注册的窗口时间已经过了，请重新获取验证码");
                response.setDate(false);
                response.setCode("-1");
                response.setErrorMessage("用户注册的窗口时间已经过了，请重新获取验证码");
                return response;
            }
            /** 校验用户的入参，入参使用spring本身的特性进行校验，这里只校验可选入参,注册时的参数分为必输参数和可选参数 */
            Integer paramVal = verifyRegistryInfo(request);
            if(paramVal == -1){
                response.setDate(false);
                response.setCode("-2");
                response.setMessage("非必须参数必须全部为空或者全部有值");
                return response;
            }
            /** 通过缓存获取注册渠道 */
            BasicEnum.REGISTRY_TYPE type = getRegistryChannel(registryPermitValue);
            if(type == BasicEnum.REGISTRY_TYPE.UNKNOWNTYPE || type == BasicEnum.REGISTRY_TYPE.MOBILE){
                logger.error("暂时不支持的注册类型");
                return AlphaResponse.error("-1","目前只支持邮箱注册");
            }
            String email = request.getEmail();
            if(email.equals(registryPermitValue) == false){
                logger.error("邮箱有错" + registryPermitValue);
                return AlphaResponse.error("-1","邮箱有错，请重新获取验证码");
            }

            /** paramVal为1时，只需要将必传参数的写入数据库，但是paramVal为2时，则将必传参数和可选参数的写入放到一个事务之中 */
            User user = setUserInfo(request);
            switch (paramVal){
                case 1:
                    if(userDBService.insertUserNessaryInfo(user) == false){
                        logger.error("插入用户必要信息失败");
                        return AlphaResponse.error("-1","插入用户信息失败,请重试");
                    }
                    break;
                case 2:
                    if(userDBService.insertUserAllInfo(user) == false){
                        logger.error("插入用户所有信息失败");
                        return AlphaResponse.error("-1","插入用户信息失败，请重试");
                    }
                    break;
                default:
                    return AlphaResponse.error("-1","参数不对");
            }
            /** 传输成功，则发出一个注册成功的消息到消息队列,消息队列暂时不做复杂的处理 */
        }catch (Exception e){
            logger.error("redis读取出现异常",e.getMessage());
            return AlphaResponse.error("-1","抱歉，系统内部出现异常，请稍后再试");
        }

        return response;
    }

    /**
     * 根据注册请求的参数设置用户信息
     * @param request
     * @return
     */
    private User setUserInfo(RegistryRequest request){
        /** 根据注册方式选择是否激活用户，email用户不激活，其它情况激活用户 */
        User user = new User();
        user.setUserName(request.getEmail());
        return user;
    }

    /**
     * 获取注册通道类型
     * @param channel
     * @return
     */
    private BasicEnum.REGISTRY_TYPE getRegistryChannel(String channel){
        Pattern mobilePattern = Pattern.compile(InitConfig.mobilePattern);
        Pattern emailPattern  = Pattern.compile(InitConfig.emailPattern);
        if(mobilePattern.matcher(channel).matches()){
            return BasicEnum.REGISTRY_TYPE.MOBILE;
        }else if(emailPattern.matcher(channel).matches()){
            return BasicEnum.REGISTRY_TYPE.MAIL;
        }else{
            return BasicEnum.REGISTRY_TYPE.UNKNOWNTYPE;
        }
    }

    /**
     * 校验非必须参数的合法性
     * @param request
     * @return
     */
    private Integer verifyRegistryInfo(RegistryRequest request){
        String country     = request.getCountry();
        String province    = request.getProvince();
        String city        = request.getCity();
        String street      = request.getStreet();
        String collage     = request.getCollage();
        String company     = request.getCompany();
        /** 全部为空 */
        if(StringUtils.isEmpty(country) &&
                StringUtils.isEmpty(province) &&
                StringUtils.isEmpty(city) &&
                StringUtils.isEmpty(street) &&
                StringUtils.isEmpty(collage) &&
                StringUtils.isEmpty(company)){
            return 1;
        }
        /** 全部有值 */
        if(StringUtils.isEmpty(country) == false &&
                StringUtils.isEmpty(province) == false &&
                StringUtils.isEmpty(city) == false &&
                StringUtils.isEmpty(street) == false &&
                StringUtils.isEmpty(collage) == false &&
                StringUtils.isEmpty(company) == false){
            return 2;
        }
        return -1;
    }

    /**
     * 激活用户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.registry.activateUser/1.0.0",method = {RequestMethod.GET})
    @Override
    public AlphaResponse<Boolean> activateUser(ActivateUserRequest request) {
        return null;
    }
}
