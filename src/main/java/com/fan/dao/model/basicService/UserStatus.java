package com.fan.dao.model.basicService;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author:fanwenlong
 * @date:2018-01-05 15:08:32
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户状态信息
 * @detail:
 */
public class UserStatus implements Serializable{
    private static final long serialVersionUID = -888002065784302261L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date operateTime;

    /**
     * 活动时间
     */
    private String activity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"operateTime\":\"")
                .append(operateTime).append('\"');
        sb.append(",\"activity\":\"")
                .append(activity).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
