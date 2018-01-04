package com.fan.dao.interfaces.user;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.ActivateUserRequest;
import com.fan.dao.model.request.RegistryCodeRequest;
import com.fan.dao.model.request.RegistryMediaRequest;
import com.fan.dao.model.request.RegistryRequest;
import com.fan.dao.model.response.RegistryMediaResponse;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:50:20
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户的注册服务
 * @detail:提供用户注册相关的服务
 */
public interface IUserRegistryService {
    /**
     * 获取用户的注册码
     * @param request
     * @return
     */
    public AlphaResponse<RegistryMediaResponse> getRegistryCode(RegistryMediaRequest request);

    /**
     * 校验注册时验证码的正确性
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> verifyRegistryCode(RegistryCodeRequest request);

    /**
     * 注册
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> registry(RegistryRequest request);

    /**
     * 激活用户
     * @param request
     * @return
     */
    public AlphaResponse<Boolean> activateUser(ActivateUserRequest request);
}
