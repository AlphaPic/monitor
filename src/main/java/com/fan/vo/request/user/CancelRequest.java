package com.fan.vo.request.user;

import com.fan.dao.model.AlphaRequest;

import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2017-12-25 14:47:43
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户取消登录请求
 */
public class CancelRequest extends AlphaRequest {
    private static final long serialVersionUID = -8506941376483807249L;

    /**
     * 用户cookie
     */
    @NotNull(message = "用户cookie不能为空")
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
