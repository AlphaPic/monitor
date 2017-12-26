package com.fan.vo.request.user;

import com.fan.dao.model.BaseRequest;

import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2017-12-25 11:46:08
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户登录的请求
 */
public class UserLoginRequest extends BaseRequest{
    private static final long serialVersionUID = 4177784208457846578L;

    /**
     * cookie
     */
    @NotNull(message = "cookie的值不能为空")
    private String monitorCookie;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    @Override
    public String getMonitorCookie() {
        return monitorCookie;
    }

    @Override
    public void setMonitorCookie(String monitorCookie) {
        this.monitorCookie = monitorCookie;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"monitorCookie\":\"")
                .append(monitorCookie).append('\"');
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
