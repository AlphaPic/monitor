package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:03:46
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户相关的配置
 * @detail:用于配置用户相关的操作常量
 */
public interface UserConfig {
    /** ******************************************注册****************************************** **/
    String REDIS_REGISTRY_ACCOUNT_LOCK                  = "REDIS_REGISTRY_ACCOUNT_LOCK_";            /** 账户是不是已经被锁住了 */
    String REDIS_REGISTRY_EMAIL_ACCOUNT_AUTHCODE        = "REDIS_REGISTRY_EMAIL_ACCOUNT_AUTHCODE_";  /** 用户验证码缓存 */
    Long   REDIS_REGISTRY_EMAIL_AUTH_TIMEOUT            = 100L;                                      /** 邮件验证码过期时间 */
    String REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT       = "REDIS_REGISTRY_ACCOUNT_LOAD_PERMIT_";     /** 用户已经取得了注册权限 */
    Long   REDIS_REGISTRY_ACCOUNT_REGISTRY_PERMIT_TIME  = 300L;                                      /** 该用户具有注册权限的时间，只有在这个时间段内可以注册 */
    String REDIS_REGISTRY_ACCOUNT_BIND_COOKIE           = "REDIS_REGISTRY_ACCOUNT_BIND_COOKIE_";     /** 用户和cookie绑定的键 */
}
