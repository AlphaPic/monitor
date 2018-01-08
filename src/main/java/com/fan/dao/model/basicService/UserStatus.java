package com.fan.dao.model.basicService;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-08 10:35:42
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户状态
 * @detail:
 */
public class UserStatus implements Serializable{
    private static final long serialVersionUID = -888002065784302261L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 激活码
     */
    private String activeCode;

    /**
     * 注册渠道
     */
    private String registryChannel;

    public UserStatus() {
    }

    public UserStatus(Integer userId, String activeCode, String registryChannel) {
        this.userId = userId;
        this.activeCode = activeCode;
        this.registryChannel = registryChannel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getRegistryChannel() {
        return registryChannel;
    }

    public void setRegistryChannel(String registryChannel) {
        this.registryChannel = registryChannel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"activeCode\":\"")
                .append(activeCode).append('\"');
        sb.append(",\"registryChannel\":\"")
                .append(registryChannel).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
