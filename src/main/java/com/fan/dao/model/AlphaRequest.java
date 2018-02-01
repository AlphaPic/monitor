package com.fan.dao.model;

import com.fan.framework.annotation.VariableVo;

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
public class AlphaRequest implements Serializable{
    private static final long serialVersionUID = 2048883537513472445L;

    /**
     * 方法名
     */
    @NotNull(message = "方法名不能为空")
    @VariableVo(comment = "方法名")
    private String method;
    /**
     * 版本
     */
    @NotNull(message = "版本号不能为空")
    @VariableVo(comment = "版本")
    private String version;
    /**
     * 登录cookie
     */
    @VariableVo(comment = "登录cookie")
    private String monitorCookie;
    /**
     * ip
     */
    @NotNull(message = "ip")
    @VariableVo(comment = "响应信息列表")
    private String ip;
    /**
     * 登录通道
     */
    @NotNull(message = "登录通道不能为空")
    @VariableVo(comment = "登录通道")
    private String loginChannel;
    /**
     * 请求信息
     */
    @VariableVo(comment = "请求信息")
    private HttpServletRequest httpRequest;
    /**
     * 响应信息
     */
    @VariableVo(comment = "响应信息")
    private HttpServletResponse httpResponse;


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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginChannel() {
        return loginChannel;
    }

    public void setLoginChannel(String loginChannel) {
        this.loginChannel = loginChannel;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpServletResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpServletResponse httpResponse) {
        this.httpResponse = httpResponse;
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
        sb.append(",\"ip\":\"")
                .append(ip).append('\"');
        sb.append(",\"loginChannel\":\"")
                .append(loginChannel).append('\"');
        sb.append(",\"httpRequest\":")
                .append(httpRequest);
        sb.append(",\"httpResponse\":")
                .append(httpResponse);
        sb.append('}');
        return sb.toString();
    }
}
