package com.fan.dao.model.monitor;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-26 20:27:02
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo的类型
 * @detail:
 */
public class VoType implements Serializable{
    private static final long serialVersionUID = 3959289082135282011L;

    /**
     * vo类型信息
     */
    @VariableVo(comment = "vo类型信息")
    private String voTypeInfo;

    public String getVoTypeInfo() {
        return voTypeInfo;
    }

    public void setVoTypeInfo(String voTypeInfo) {
        this.voTypeInfo = voTypeInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"voTypeInfo\":\"")
                .append(voTypeInfo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
