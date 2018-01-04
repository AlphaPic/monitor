package com.fan.dao.model.request;


import com.fan.dao.model.AlphaRequest;

import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2017-12-10 17:04:10
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:消息队列状态请求
 */
public class MessageQueueStatusRequest extends AlphaRequest {
    private static final long serialVersionUID = -4608447012023024362L;

    @NotNull(message = "name can't be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
