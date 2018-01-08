package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:59:58
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户激活请求
 * @detail:
 */
public class ActivateUserRequest extends AlphaRequest {
    private static final long serialVersionUID = -878972964606657170L;
    /**
     * 用户id
     */
    @NotNull(message = "cookie不能为空")
    private String alphaCookie;

    /**
     * 激活码
     */
    @NotNull(message = "激活码不能为空")
    private String activeCode;

    /**
     * 注册渠道
     */
    @NotNull(message = "注册渠道不能为空")
    private String registryChannel;

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
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
        sb.append("\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append(",\"activeCode\":\"")
                .append(activeCode).append('\"');
        sb.append(",\"registryChannel\":\"")
                .append(registryChannel).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
