package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2017-12-25 17:34:19
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:加密类型枚举
 */
public enum EncryptEnum {
    /** ------------加密------------- */
    RSA("RSA"),
    DES("DES"),
    DES3("DES-3"),
    AES("AES"),

    /** -------------签名------------ */
    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256");

    private String name;

    EncryptEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
