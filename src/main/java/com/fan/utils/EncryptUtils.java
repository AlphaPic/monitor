package com.fan.utils;

import com.fan.consts.EncryptEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author:fanwenlong
 * @date:2017-12-25 16:13:16
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:校验所使用工具
 */
public class EncryptUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

    /** md5加密对象 */
    private static MessageDigest md5 = null;
    private static MessageDigest sha1 = null;
    private static MessageDigest sha256 = null;

    /** 加载静态资源,静态资源是加密和计算hash的对象 */
    static {
        try{
            md5     = MessageDigest.getInstance("MD5");
            sha1    = MessageDigest.getInstance("SHA-1");
            sha256  = MessageDigest.getInstance("SHA-256");
        }catch (Exception e){
            logger.error("在获取加密对象的时候失败" + e.getMessage());
        }
    }


    public static String generateHash(EncryptEnum type,String...members){
        /** 暂时只支持SHA-256 */
        if(type.equals(EncryptEnum.SHA_256) == false
                && type.equals(EncryptEnum.SHA_1) == false
                && type.equals(EncryptEnum.MD5) == false){
            logger.error("类型只能为sha256,sha1,md5三种");
            return null;
        }
        StringBuilder sb = new StringBuilder(100);
        for(String member : members){
            sb.append(member);
        }
        String origin = sb.toString();

        String res = null;
        switch (type){
            case MD5:/** md5签名校验 */
                md5.update(origin.getBytes());
                byte[] mdBytes = md5.digest();
                res = new BigInteger(1,mdBytes).toString();
                break;
            case SHA_1:/** sha-1加密 */
                sha1.update(origin.getBytes());
                byte[] sha1Bytes = sha1.digest();
                res = new BigInteger(1,sha1Bytes).toString();
                break;
            case SHA_256:/** sha-256加密 */
                sha1.update(origin.getBytes());
                byte[] sha256Bytes = sha256.digest();
                res = new BigInteger(1,sha256Bytes).toString();
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 将10进制变成16进制,每隔4位用一个‘-’来分隔
     * @param number
     * @return
     */
    public static String exchangeTentoHex(String number){
        return number;
    }

    public static void main(String[] args){
        logger.info(generateHash(EncryptEnum.MD5,"hello"));
        logger.info(generateHash(EncryptEnum.SHA_1,"hello"));
        logger.info(generateHash(EncryptEnum.SHA_256,"hello"));
    }
}
