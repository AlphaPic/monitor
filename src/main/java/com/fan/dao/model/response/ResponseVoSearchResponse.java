package com.fan.dao.model.response;

import com.fan.dao.model.monitor.ResponseInfo;
import com.fan.dao.model.monitor.VoType;

import java.io.Serializable;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:02:56
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:响应vo查找响应
 * @detail:
 */
public class ResponseVoSearchResponse extends VoType implements Serializable{
    private static final long serialVersionUID = -6664529518901317073L;

    /**
     * 响应信息列表
     */
    private List<ResponseInfo> responseInfoList;

    public List<ResponseInfo> getResponseInfoList() {
        return responseInfoList;
    }

    public void setResponseInfoList(List<ResponseInfo> responseInfoList) {
        this.responseInfoList = responseInfoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"responseInfoList\":")
                .append(responseInfoList);
        sb.append('}');
        return sb.toString();
    }
}
