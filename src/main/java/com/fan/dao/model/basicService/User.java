package com.fan.dao.model.basicService;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:fanwenlong
 * @date:2017-12-27 14:52:51
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户信息
 * @detail:
 */
public class User implements Serializable{
    private static final long serialVersionUID = 8410842405931439043L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户创建时间
     */
    private Date createTime;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 权限
     */
    private Integer permit;
    /**
     * 状态
     */
    private String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastLoginTime\":\"")
                .append(lastLoginTime).append('\"');
        sb.append(",\"mobile\":\"")
                .append(mobile).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"permit\":")
                .append(permit);
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
