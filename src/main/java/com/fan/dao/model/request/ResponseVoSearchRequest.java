package com.fan.dao.model.request;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:02:09
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:响应vo查找请求
 * @detail:
 */
public class ResponseVoSearchRequest implements Serializable {
    private static final long serialVersionUID = -6022107631263196208L;

    /**
     * 类名
     */
    @VariableVo(comment = "类名",demoVal = "CityResponse")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"className\":\"")
                .append(className).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
