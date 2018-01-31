package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2018-01-24 09:48:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:消息请求
 * @detail:
 */
public class MessageRequest extends AlphaRequest{
    private static final long serialVersionUID = -4358794848869502536L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 消息的读取状态
     * 0--未读
     * 1--已读
     * 2--所有信息
     */
    private Integer msgStatus;

    /**
     * 消息的类型
     * 0--错误信息
     * 1--警告信息
     * 2--一般信息
     * 3--所有信息
     */
    private Integer msgType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"msgStatus\":")
                .append(msgStatus);
        sb.append(",\"msgType\":")
                .append(msgType);
        sb.append('}');
        return sb.toString();
    }
}
