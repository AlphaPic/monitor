package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;


/**
 * @author:fanwenlong
 * @date:2018-01-25 16:45:28
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:Request vo的查找请求
 * @detail:
 */
public class RequestVoSearchRequest extends AlphaRequest{
    private static final long serialVersionUID = 6712633654362857004L;

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
