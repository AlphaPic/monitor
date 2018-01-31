package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2018-01-25 19:44:14
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:类的请求信息
 * @detail:
 */
public class ClassVoSearchRequest extends AlphaRequest{
    private static final long serialVersionUID = -6473557293424421747L;

    /**
     * 类名
     */
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
