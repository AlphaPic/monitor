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
public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = 4719166322183167850L;

    private String code = "1";
    private String message = "";
    private String errorMessage = "";
    private String debugMsg;

    private T date;

    public BaseResponse(T date, String code, String message, String errorMessage) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public BaseResponse() {
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(data, "1", null, null);
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<T>(data, "1", message, null);
    }

    public static <T> BaseResponse<T> success(String code, T data,String message,String errorMessage) {
        return new BaseResponse<T>(data, code, message, errorMessage);
    }

    public static <T> BaseResponse<T> error(String code, String errorMessage) {
        return new BaseResponse<T>(null, code, errorMessage, errorMessage);
    }

    public static <T> BaseResponse<T> error(String code, String message, String errorMessage) {
        return new BaseResponse<T>(null, code, message, errorMessage);
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
