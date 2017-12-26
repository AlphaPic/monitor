package com.fan.vo.request.user;

import com.fan.dao.model.BaseRequest;

import javax.validation.constraints.NotNull;

/**
 * @author:fanwenlong
 * @date:2017-12-25 14:47:43
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户取消登录请求
 */
public class CancelRequest extends BaseRequest{
    private static final long serialVersionUID = -8506941376483807249L;

    /**
     * 用户cookie
     */
    @NotNull(message = "用户cookie不能为空")
    private String monitorCookie;

    @Override
    public String getMonitorCookie() {
        return monitorCookie;
    }

    @Override
    public void setMonitorCookie(String monitorCookie) {
        this.monitorCookie = monitorCookie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"monitorCookie\":\"")
                .append(monitorCookie).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
