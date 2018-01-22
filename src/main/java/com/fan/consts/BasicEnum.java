package com.fan.consts;

import org.springframework.util.StringUtils;

/**
 * @author:fanwenlong
 * @date:2017-12-25 19:54:14
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:基本的枚举
 */
public class BasicEnum {
    /** 登录通道 */
    public static enum  LOGIN_CHANNEL{
        TOUCH,
        ANDROID,
        IOS;
    }

    /** 用户的登录状态 */
    public static enum LOGIN_STATUS{
        LOGIN,
        LOGOUT;
    }

    /** 支持的注册类型 */
    public static enum REGISTRY_TYPE{
        MAIL("email"),
        MOBILE("mobile"),
        UNKNOWNTYPE("unknown");

        private String name;

        REGISTRY_TYPE(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 判断注册类型支不支持
         * @param type
         * @return
         */
        public static REGISTRY_TYPE getRegistryTypeSupport(String type){
            if(StringUtils.isEmpty(type) == true){
                return REGISTRY_TYPE.UNKNOWNTYPE;
            }
            for(REGISTRY_TYPE registryType : REGISTRY_TYPE.values()){
                if(registryType == REGISTRY_TYPE.UNKNOWNTYPE){
                    continue;
                }
                if(registryType.getName().equals(type)){
                    return registryType;
                }
            }
            return REGISTRY_TYPE.UNKNOWNTYPE;
        }
    }

    public static enum MAIL_SUPPORT{
        MAIL_GOOGLE("gmail.com"),
        MAIL_QQ("qq.com"),
        MAIL_163("163.com"),
        MAIL_126("126.com");

        private String mailSuffix;

        MAIL_SUPPORT(String mailSuffix) {
            this.mailSuffix = mailSuffix;
        }

        public String getMailSuffix() {
            return mailSuffix;
        }

        public void setMailSuffix(String mailSuffix) {
            this.mailSuffix = mailSuffix;
        }

        /**
         * 判断邮箱后缀是不是支持
         * @param email
         * @return
         */
        public static Boolean isMailSupport(String email){
            if(StringUtils.isEmpty(email) == true || email.contains("@") == false){
                return false;
            }
            String userName = email.split("@")[0];
            String suffix   = email.split("@")[1];
            /** 用户名和后缀均不能为空 */
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(suffix)){
                return false;
            }
            for(MAIL_SUPPORT mail : MAIL_SUPPORT.values()){
                if(mail.getMailSuffix().equals(suffix)){
                    return true;
                }
            }
            return false;
        }
    }

    /** 用户的权限 */
    public static enum USER_PERMIT{
        ADMIN(1),      /** 管理员权限 */
        COMMON(2),     /** 普通用户权限 */
        VIEWER(3),     /** 浏览者的权限 */
        FROZEN(4);     /** 账户被冻结 */

        private Integer permit_code;

        USER_PERMIT(Integer permit_code) {
            this.permit_code = permit_code;
        }

        public Integer getPermit_code() {
            return permit_code;
        }

        public void setPermit_code(Integer permit_code) {
            this.permit_code = permit_code;
        }
    }

    public static void main(String[] args){
        System.out.println(LOGIN_CHANNEL.ANDROID.toString());
    }
}
