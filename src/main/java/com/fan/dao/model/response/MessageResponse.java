package com.fan.dao.model.response;


import java.io.Serializable;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-24 09:46:53
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:消息的响应
 * @detail:
 */
public class MessageResponse implements Serializable{
    private static final long serialVersionUID = -6359533050060740592L;

    private List<MessageInfoElem> messageList;

    public List<MessageInfoElem> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageInfoElem> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"messageList\":")
                .append(messageList);
        sb.append('}');
        return sb.toString();
    }
}
