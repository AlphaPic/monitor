package com.fan.dao.interfaces.user;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.basicService.User;
import com.fan.vo.request.user.CancelRequest;
import com.fan.vo.request.user.CookieRequest;
import com.fan.vo.request.user.UserInfoRequest;
import com.fan.vo.request.user.UserLoginRequest;
import com.fan.vo.response.user.UserInfoResponseVo;

/**
 * @author:fanwenlong
 * @date:2017-12-25 11:27:24
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录的接口
 */
public interface IUserLoginService {

    /**
     * 登录
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> login(UserLoginRequest request);

    /**
     * 取消登录
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> cancelLogin(CancelRequest request);


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public AlphaResponse<User> getUserInfo(UserInfoRequest request);
}
