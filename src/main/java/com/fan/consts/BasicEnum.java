package com.fan.consts;

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
