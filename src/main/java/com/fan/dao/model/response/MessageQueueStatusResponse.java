package com.fan.dao.model.response;

import com.fan.framework.annotation.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2017-12-10 17:05:12
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:消息队列状态响应
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageQueueStatusResponse implements Serializable{
    private static final long serialVersionUID = 5148646086007650351L;

    private Integer queueNum;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(Integer queueNum) {
        this.queueNum = queueNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"queueNum\":")
                .append(queueNum);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
