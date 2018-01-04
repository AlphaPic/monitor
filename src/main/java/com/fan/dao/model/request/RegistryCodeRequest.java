package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:59:44
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户验证码请求
 * @detail:
 */
public class RegistryCodeRequest extends AlphaRequest {
    private static final long serialVersionUID = -4087714207160945153L;

    /**
     * cookie
     */
    private String alphaCookie;

    /**
     * 验证码
     */
    private String authCode;

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append(",\"authCode\":\"")
                .append(authCode).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
