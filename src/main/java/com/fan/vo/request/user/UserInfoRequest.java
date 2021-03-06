package com.fan.vo.request.user;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2017-12-25 14:48:56
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录信息请求
 */
public class UserInfoRequest extends AlphaRequest {
    private static final long serialVersionUID = -5138136194619438945L;

    /**
     * 用户cookie
     */
    private String alphaCookie;

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
