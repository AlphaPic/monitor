package com.fan.impl.user;

import com.fan.consts.EncryptEnum;
import com.fan.dao.interfaces.baseService.IUserCacheService;
import com.fan.dao.interfaces.user.IUserSecurityService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.User;
import com.fan.dao.model.basicService.UserSecurity;
import com.fan.dao.model.request.UserSecurityRequest;
import com.fan.framework.annotation.MonitorController;
import com.fan.impl.baseService.UserDBServiceImpl;
import com.fan.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:fanwenlong
 * @date:2018-01-08 20:38:05
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户安全相关的服务
 * @detail:
 */
@MonitorController
public class UserSecurityServiceImpl implements IUserSecurityService{
    private static final Logger logger = LoggerFactory.getLogger(UserSecurityServiceImpl.class);

    @Autowired
    private UserDBServiceImpl userDBService;

    @Autowired
    private IUserCacheService userCacheService;

    /**
     * 保存密码
     * 保存密码是在用户具有登录权限下才能操作的事情
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.security.savePassword/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Override
    public AlphaResponse<Boolean> savePassword(UserSecurityRequest request) {
        String cookie   = request.getAlphaCookie();
        String pawword  = request.getPassword();
        /** 只有注册权限的用户才能设置密码 */
        if(userCacheService.doUserHasRegistryPermit(cookie) == false){
            logger.error("没有注册权限");
            return AlphaResponse.error("-1","没有注册权限，不能设置密码");
        }

        String passwordHash = EncryptUtils.generateHash(EncryptEnum.SHA_256,pawword);
        String userName = userCacheService.getUserNameByCookie(cookie);

        try{
            if(userDBService.getUserPasswordInfo(userName) != null){
                logger.error("用户密码已存在");
                return AlphaResponse.error("-1","用户密码已经存在");
            }
            UserSecurity security = new UserSecurity();
            security.setUserName(userName);
            security.setUserHash(passwordHash);
            userDBService.saveUserPassword(security);
        }catch (Exception e){
            logger.error("写入密码出现异常" + e.getMessage());
            return AlphaResponse.error("-1","写入密码出现异常");
        }
        return AlphaResponse.success(true,"密码保存成功");
    }

    /**
     * 修改密码
     * 修改密码的用户必须获取已登录的权限，如果用户不处于登录的状态
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.security.updatePassword/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Override
    public AlphaResponse<Boolean> changePassword(UserSecurityRequest request) {
//        String
        return null;
    }
}
