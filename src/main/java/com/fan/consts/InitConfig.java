package com.fan.consts;

/**
 * @author:fanwenlong
 * @date:2017-12-20 21:33:47
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:初始化的配置
 * @detail:这是配置的常量,先放在这里，在随后会使用线上的方式进行配置
 */
public interface InitConfig {
    /** 过滤器开关控制 */
    Boolean FILTER_LOGIN_BUTTON           = false;  //登录过滤器的开关控制(true打开,false关闭)
    Boolean FILTER_REQUEST_PATTERN_BUTTON = false;  //请求表达式过滤器开关控制(true打开,false关闭)
    Boolean FILTER_SIMPLE_BUTTON          = false;  //简单的过滤器控制(true打开,false关闭)

    /** 拦截器开关控制 */
    Boolean INTERCEPTOR_LOGIN_BUTTON      = false;  //登录拦截器开关控制(true打开,false关闭)
    Boolean INTERCEPTOR_SIGNATURE_BUTTON  = false;  //签名拦截器的开关控制(true打开,false关闭)

    /** JSP viewResolver的匹配 */
    String JspResolverPrefix = "/WEB-INF/";        //前缀
    String JspResolverSuffix = ".jsp";             //后缀

    /** 登录拦截器匹配路径 */
    String LoginInterceptorPattern_0 = "/*";
    String SignatureInterceptorPattern = "/*";

    /** 正则匹配 */
    String ip4Pattern       = "([1-9]|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}"; //是否是正确的ip
    String mobilePattern    = "1[0-9]{10}";                                                                                                 //手机号正则表达式
    String emailPattern     = "([0-9]{8,12}@qq).com|([a-z0-9]{6,20}@(163|gmail)).com";                                                      //邮箱正则表达式

    //@[qq|163|gmail]\.com
    /** redis的相关配置 */
    Long userSurviveTime = 1800L;             //用户cookie的存活时间

    /** redis常量 */
    String LOGIN_FAIL  = "LOGIN_FAIL_";        //登录失败次数
    String LIMIT_HOST  = "LIMIT_HOST_";        //受限制的host
    String COOKIE_ID   = "COOKIE_ID_";         //用户状态(一般拿cookie来获取userId)

    /** 登录相关 */
    String TWO_MINUTE_LOGIN_LIMIT = "TWO_MINUTE_LOGIN_LIMIT_";                     //两分钟内的登录接口调用限制
    String TWO_MINUTE_LOGIN_PERMIT_LIMIT = "TWO_MINUTE_LOGIN_PERMIT_LIMIT_";       //两分钟内的登录权限接口调用限制
    String TWO_MINUTE_INTERFACE_CALL_LIMIT = "TWO_MINUTE_INTERFACE_CALL_LIMIT_";   //两分钟内的接口调用限制

    /** signal的签名值,使用这个可以免去校验签名 */
    String SIGNATURE = "e878766-12a1-9b766e";
    /** MD5的盐 */
    String MD5_SALT="AKSDHAHSDFAZXVBAAJWKERGHQFSKDMABLFAJKHSJKDFHIAWYHERIOOHFSDJKBFKHDAKL";


    /** 是否允许debug模式 */
    Boolean DEBUG_MODE = true;

    /** ------------------------------------------------Context上下文配置-------------------------------------------------- **/
    /** redis连接池相关配置 */
    Integer REDIS_MAXIDLE      = 100;      /** 最大的空闲数 */
    Integer REDIS_MAXACTIVE    = 100;      /** 最大的活跃数 */
    Integer REDIS_MAXWAIT      = 1000;     /** 最长等待时间 */
    Boolean REDIS_TESTONBORROW = true;     /** 未知 */

    String REDIS_HOST  = "localhost";       /** redis主机ip */
    Integer REDIS_PORT = 6379;              /** redis端口 */
    String REDIS_AUTH  = null;              /** 密码 */
    Integer REDIS_TIMEOUT = 100;            /** 超时时间 */

    /** 数据库相关配置 */
    /** Mysql */
    String MYSQL_DRIVER        = "com.mysql.jdbc.Driver";                  /** mysql驱动名称 */
    String MYSQL_URL           = "jdbc:mysql://10.115.1.223:3306/monitor"; /** mysql驱动url */
    String MYSQL_USERNAME      = "root";                                   /** mysql用户名 */
    String MYSQL_PASSWORD      = "xiwang";                                 /** mysql密码 */

    String MYSQL_ENVIRONMENT   = "mysqlEnvironment";                       /** mysql的环境名称 */

    /** Zookeeper的相关配置 */
    String  ZK_SERVER_IP     = "localhost";       /** zookeeper的ip */
    Integer ZK_SERVER_PORT   = 2181;              /** zookeeper的端口 */
    Integer ZK_TIMEOUT       = 10000;             /** 超时时间 */

    /** Kafka的相关配置 */
    String  KAFKA_COMMON_BOOTSTRAP_SERVER           = "localhost:19921";                                            /** kafka服务器的url */
    String  KAFKA_COMMON_TOPIC                      = "topic_for_test";                                             /** 测试的主题 */

    /** Kafka的Producer配置 */
    String  KAFKA_PRODUCER_ACKS                     = "all";                                                         /**  */
    Integer KAFKA_PRODUCER_RETRIES                  = 0;                                                             /** 表明重发的次数为0 */
    Integer KAFKA_PRODUCER_BATCH_SIZE               = 16384;                                                         /** 未发送成功的消息存放的缓存的大小 */
    Integer KAFKA_PRODUCER_LINGER_MS                = 1;                                                             /** 消息可以立即发送，也可以稍后发送，这个用来控制要等多久之后发送的时间 */
    Long    KAFKA_PRODUCER_BUFFER_MEMORY            = 33554432L;                                                     /** 生产者的缓存大小 */
    String  KAFKA_PRODUCER_KEY_SERIALIZER           = "org.apache.kafka.common.serialization.StringSerializer";      /** 键的序列化工具 */
    String  KAFKA_PRODUCER_VALUE_SERIALIZER         = "org.apache.kafka.common.serialization.StringSerializer";      /** 值的序列化工具 */

    /** Kafka的Consumer配置 */
    String KAFKA_CONSUMER_GROUP_ID                  = "test";                                                        /** 所属组的id */
    String KAFKA_CONSUMER_ENABLE_AUTO_COMMIT        = "true";                                                        /** 是否允许自动提交 */
    String KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL_MS   = "1000";                                                        /** 自动提交间隔 */
    String KAFKA_CONSUMER_KEY_DESERIALIZER          = "org.apache.kafka.common.serialization.StringDeserializer";    /** 键的反序列化工具 */
    String KAFKA_CONSUMER_VALUE_DESERIALIZER        = "org.apache.kafka.common.serialization.StringDeserializer";    /** 值的反序列化工具 */
    Long   KAFKA_CONSUMER_RECORDS_TIMEOUT           = 1000L;                                                         /** 获取消息的超时时间 */

    /** ActiveMQ的相关配置 */
    String ACTIVEMQ_USERNAME                       = "admin";                                                        /** activeMQ用户名 */
    String ACTIVEMQ_PASSWORD                       = "admin";                                                        /** activeMQ密码 */
    String ACTIVEMQ_BROKENURL                      = "localhost:8161";                                               /** activeMQ的连接地址 */
    String ACTIVEMQ_DEFAULT_QUEUE                  = "QUEUE1";                                                       /** activeMQ的目标队列 */

    /** 邮件的相关配置 */
    String  MAIL_QQ_SMTP_HOST                       = "smtp.qq.com";                                                  /** QQ的smtp的主机 */
    Integer MAIL_QQ_SMTP_PORT                       = 1;
    String  MAIL_QQ_SMTP_USERNAME                   = "1693293713@qq.com";                                            /** 邮箱名称 */
    String  MAIL_QQ_SMTP_AUTHCODE                   = "kwfitspfcxpmbaji";                                             /** 授权码 */
//    String  MAIL_QQ_SMTP_
}
