package com.fan.dao.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2017-12-10 16:35:06
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:基本的请求
 */
public class BaseRequest implements Serializable{
    private static final long serialVersionUID = 2048883537513472445L;

    /**
     * 方法名
     */
    @NotNull(message = "方法名不能为空")
    private String method;
    /**
     * 版本
     */
    @NotNull(message = "版本号不能为空")
    private String version;
    /**
     * 登录cookie
     */
    private String monitorCookie;
    /**
     * 请求信息
     */
    private HttpServletRequest request;
    /**
     * 响应信息
     */
    private HttpServletResponse response;
    /**
     * 用户ID
     */
    private Long userId;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMonitorCookie() {
        return monitorCookie;
    }

    public void setMonitorCookie(String monitorCookie) {
        this.monitorCookie = monitorCookie;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"method\":\"")
                .append(method).append('\"');
        sb.append(",\"version\":\"")
                .append(version).append('\"');
        sb.append(",\"monitorCookie\":\"")
                .append(monitorCookie).append('\"');
        sb.append(",\"request\":")
                .append(request);
        sb.append(",\"response\":")
                .append(response);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append('}');
        return sb.toString();
    }
}
