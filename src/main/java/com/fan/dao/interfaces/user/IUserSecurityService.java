package com.fan.dao.interfaces.user;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.UserSecurity;
import com.fan.dao.model.request.UserSecurityRequest;

/**
 * @author:fanwenlong
 * @date:2018-01-08 20:38:35
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户安全相关操作
 * @detail:
 */
public interface IUserSecurityService {
    /**
     * 保存密码
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> savePassword(UserSecurityRequest request);

    /**
     * 修改密码
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> changePassword(UserSecurityRequest request);
}
