package com.fan.dao.model.response;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:58:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户注册媒介响应
 * @detail:
 */
public class RegistryMediaResponse implements Serializable {
    private static final long serialVersionUID = -211610194762664596L;

    /**
     * 是否成功
     */
    @VariableVo(comment = "是否成功")
    private Boolean isSuccess;

    /**
     * cookie
     */
    @VariableVo(comment = "cookie")
    private String alphaCookie;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"isSuccess\":")
                .append(isSuccess);
        sb.append(",\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
