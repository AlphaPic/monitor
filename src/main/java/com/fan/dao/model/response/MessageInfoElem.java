package com.fan.dao.model.response;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:fanwenlong
 * @date:2018-01-24 09:48:08
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:消息详情
 * @detail:
 */
public class MessageInfoElem implements Serializable{
    private static final long serialVersionUID = -351690319155534393L;

    /**
     * 消息体
     */
    @VariableVo(comment = "消息体")
    private String msg;

    /**
     * 消息的产生时间
     */
    @VariableVo(comment = "消息的产生时间")
    private Date msgProduceDate;

    /**
     * 消息是否已被阅读
     * true--已被阅读
     * false--未被阅读
     */
    @VariableVo(comment = "消息是否已被阅读")
    private Boolean isReaded;

    /**
     * 消息的生产者
     */
    @VariableVo(comment = "消息的生产者")
    private String msgProducer;

    /**
     * 消息的消费者
     */
    @VariableVo(comment = "消息的消费者")
    private String msgConsumer;

    /**
     * 消息类型
     * 0--错误信息
     * 1--警告信息
     * 2--一般信息
     * 3--所有信息
     */
    @VariableVo(comment = "消息类型")
    private String msgType;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getMsgProduceDate() {
        return msgProduceDate;
    }

    public void setMsgProduceDate(Date msgProduceDate) {
        this.msgProduceDate = msgProduceDate;
    }

    public Boolean getReaded() {
        return isReaded;
    }

    public void setReaded(Boolean readed) {
        isReaded = readed;
    }

    public String getMsgProducer() {
        return msgProducer;
    }

    public void setMsgProducer(String msgProducer) {
        this.msgProducer = msgProducer;
    }

    public String getMsgConsumer() {
        return msgConsumer;
    }

    public void setMsgConsumer(String msgConsumer) {
        this.msgConsumer = msgConsumer;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"msgProduceDate\":\"")
                .append(msgProduceDate).append('\"');
        sb.append(",\"isReaded\":")
                .append(isReaded);
        sb.append(",\"msgProducer\":\"")
                .append(msgProducer).append('\"');
        sb.append(",\"msgConsumer\":\"")
                .append(msgConsumer).append('\"');
        sb.append(",\"msgType\":\"")
                .append(msgType).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
