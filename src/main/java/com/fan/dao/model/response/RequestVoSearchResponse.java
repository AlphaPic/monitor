package com.fan.dao.model.response;

import com.fan.dao.model.monitor.RequestInfo;
import com.fan.dao.model.monitor.VoType;
import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 16:58:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:Request vo的查找响应
 * @detail:
 */
public class RequestVoSearchResponse extends VoType implements Serializable{
    private static final long serialVersionUID = -3451746592067824091L;

    /**
     * 类的请求信息列表
     */
    @VariableVo(comment = "类的请求信息列表")
    private List<RequestInfo> requestInfoList;

    public List<RequestInfo> getRequestInfoList() {
        return requestInfoList;
    }

    public void setRequestInfoList(List<RequestInfo> requestInfoList) {
        this.requestInfoList = requestInfoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"requestInfoList\":")
                .append(requestInfoList);
        sb.append('}');
        return sb.toString();
    }
}
