package com.fan.impl.user;

import com.fan.dao.interfaces.user.IUserSecurityService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.UserSecurity;
import com.fan.dao.model.request.UserSecurityRequest;
import com.fan.framework.annotation.MonitorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 保存密码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.security.savepassword/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Override
    public AlphaResponse<Boolean> savePassword(UserSecurityRequest request) {
        return null;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.user.security.updatepassword/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Override
    public AlphaResponse<Boolean> changePassword(UserSecurityRequest request) {
        return null;
    }
}
