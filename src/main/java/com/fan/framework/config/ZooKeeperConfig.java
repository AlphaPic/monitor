package com.fan.framework.config;

import com.fan.consts.InitConfig;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author:fanwenlong
 * @date:2017-12-29 15:22:32
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:zookeeper的配置
 * @detail:配置zookeeper的基本信息
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ZooKeeperConfig {
    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperConfig.class);

    @Bean
    public ZooKeeper getZooKeeper(){
        String  url     = InitConfig.ZK_SERVER_IP + ":" + InitConfig.ZK_SERVER_PORT;
        Integer timeout = InitConfig.ZK_TIMEOUT;
        ZooKeeper zk = null;
        try {
             zk = new ZooKeeper(url, timeout, null);
        }catch (Exception e){
            logger.error("创建zookeeper失败");
        }
        return zk;
    }
}
