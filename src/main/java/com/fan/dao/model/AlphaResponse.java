package com.fan.dao.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2017-12-10 16:37:03
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:基本的响应
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AlphaResponse<T> implements Serializable{
    private static final long serialVersionUID = 4719166322183167850L;

    private String code = "1";
    private String message = "";
    private String errorMessage = "";
    private String debugMsg;

    private T date;

    public AlphaResponse(T date, String code, String message, String errorMessage) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public AlphaResponse() {
    }

    public static <T> AlphaResponse<T> success(T data) {
        return new AlphaResponse<T>(data, "1", null, null);
    }

    public static <T> AlphaResponse<T> success(T data, String message) {
        return new AlphaResponse<T>(data, "1", message, null);
    }

    public static <T> AlphaResponse<T> success(String code, T data, String message, String errorMessage) {
        return new AlphaResponse<T>(data, code, message, errorMessage);
    }

    public static <T> AlphaResponse<T> error(String code, String errorMessage) {
        return new AlphaResponse<T>(null, code, errorMessage, errorMessage);
    }

    public static <T> AlphaResponse<T> error(String code, String message, String errorMessage) {
        return new AlphaResponse<T>(null, code, message, errorMessage);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
