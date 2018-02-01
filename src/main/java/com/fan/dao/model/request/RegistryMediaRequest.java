package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;
import com.fan.framework.annotation.VariableVo;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:59:34
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户注册媒介请求
 * @detail:
 */
public class RegistryMediaRequest extends AlphaRequest{
    private static final long serialVersionUID = 6225990823707548763L;
    /**
     * 注册类型
     */
    @VariableVo(comment = "注册类型",demoVal = "MOBILE")
    private String registryType;

    /**
     * 注册邮件
     */
    @VariableVo(comment = "注册邮件",demoVal = "alpha18603074401@gmail.com")
    private String email;

    /**
     * 注册手机
     */
    @VariableVo(comment = "注册手机",demoVal = "18603074401")
    private String mobile;

    public String getRegistryType() {
        return registryType;
    }

    public void setRegistryType(String registryType) {
        this.registryType = registryType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"registryType\":\"")
                .append(registryType).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"mobile\":\"")
                .append(mobile).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
