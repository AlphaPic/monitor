package com.fan.dao.model.basicService;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-08 20:31:44
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户安全对象
 * @detail:
 */
public class UserSecurity implements Serializable{
    private static final long serialVersionUID = -3901856223552720494L;
    /**
     * 用户id
     */
    private String userName;
    /**
     * 用户密码的hash值
     */
    private String userHash;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"userHash\":\"")
                .append(userHash).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
