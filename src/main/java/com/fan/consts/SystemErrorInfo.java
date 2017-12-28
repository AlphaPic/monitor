package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2017-12-27 21:24:09
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:系统错误信息
 * @detail:现在暂时不使用，留待稍后开发
 */
public enum SystemErrorInfo {
    cookieNotFound(-1,"没有找到cookie");
    private Integer errorCode;
    private String errorMsg;

    SystemErrorInfo(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
