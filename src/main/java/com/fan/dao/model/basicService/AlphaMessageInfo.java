package com.fan.dao.model.basicService;

import java.io.Serializable;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-04 15:14:57
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:邮件的信息详情
 * @detail:
 */
public class AlphaMessageInfo implements Serializable{
    private static final long serialVersionUID = -2280825413316407892L;

    /**
     * 发送者
     */
    private String from;

    /**
     * 接受者(单个)
     */
    private String singleDest;

    /**
     * 接受者(多个人)
     */
    private List<String> multiDest;

    /**
     * 是否发送给多个人
     */
    private Boolean isMultiSender;

    /**
     * 正文
     */
    private String text;

    public AlphaMessageInfo() {
        isMultiSender = false;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSingleDest() {
        return singleDest;
    }

    public void setSingleDest(String singleDest) {
        this.singleDest = singleDest;
    }

    public List<String> getMultiDest() {
        return multiDest;
    }

    public void setMultiDest(List<String> multiDest) {
        this.multiDest = multiDest;
    }

    public Boolean getMultiSender() {
        return isMultiSender;
    }

    public void setMultiSender(Boolean multiSender) {
        isMultiSender = multiSender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"from\":\"")
                .append(from).append('\"');
        sb.append(",\"singleDest\":\"")
                .append(singleDest).append('\"');
        sb.append(",\"multiDest\":")
                .append(multiDest);
        sb.append(",\"isMultiSender\":")
                .append(isMultiSender);
        sb.append(",\"text\":\"")
                .append(text).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
