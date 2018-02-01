package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;
import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-08 20:43:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户安全请求
 * @detail:
 */
public class UserSecurityRequest extends AlphaRequest {
    private static final long serialVersionUID = -3937507045451358648L;
    /**
     * 用户cookie
     */
    @VariableVo(comment = "用户cookie",demoVal = "alksdfahsdfhaio164892364981238974")
    private String alphaCookie;
    /**
     * 密码
     */
    @VariableVo(comment = "密码",demoVal = "xiwang")
    private String password;
    /**
     * 原密码hash
     */
    @VariableVo(comment = "原密码hash",demoVal = "aklshdfahjklsdfhajklhdfjklahjklsdhfjklah")
    private String passwordOriginHash;
    /**
     * 新密码hash
     */
    @VariableVo(comment = "新密码hash",demoVal = "qiowyeiryiwueyzxcbvjzbsdhaklfhlasdh")
    private String passwordNewHash;

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
    }

    public String getPasswordOriginHash() {
        return passwordOriginHash;
    }

    public void setPasswordOriginHash(String passwordOriginHash) {
        this.passwordOriginHash = passwordOriginHash;
    }

    public String getPasswordNewHash() {
        return passwordNewHash;
    }

    public void setPasswordNewHash(String passwordNewHash) {
        this.passwordNewHash = passwordNewHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"passwordOriginHash\":\"")
                .append(passwordOriginHash).append('\"');
        sb.append(",\"passwordNewHash\":\"")
                .append(passwordNewHash).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
