package com.fan.framework.config;

import com.fan.consts.InitConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author:fanwenlong
 * @date:2017-12-27 16:17:34
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:redis的相关配置
 * @detail:配置好redis的相关内容
 */
@Configuration
/** 下面这个是强制启用cglib进行代理 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RedisConfig {
    @Bean
    public JedisPoolConfig getPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(InitConfig.REDIS_MAXIDLE);
        config.setTestOnBorrow(InitConfig.REDIS_TESTONBORROW);
        config.setMaxWaitMillis(InitConfig.REDIS_MAXWAIT);
        return config;
    }

    @Bean
    public JedisConnectionFactory getRedisConnectionFactory(JedisPoolConfig config){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(InitConfig.REDIS_HOST);
        factory.setPort(InitConfig.REDIS_PORT);
        factory.setTimeout(InitConfig.REDIS_TIMEOUT);
        factory.setPassword(InitConfig.REDIS_AUTH);
        factory.afterPropertiesSet();
        return factory;
    }

    /** key-value的类型为String-String类型的模板 */
    @Bean
    public RedisTemplate<String,String> getStringRedisTemplate(RedisConnectionFactory cf){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /** key-value的类型为String-Object类型的模板 */
    @Bean
    public RedisTemplate<String,Object> getObjectRedisTemplate(RedisConnectionFactory cf){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    public static void main(String[] args){

        RedisConfig config = new RedisConfig();
        JedisConnectionFactory cf = config.getRedisConnectionFactory(config.getPoolConfig());


        RedisTemplate redisTemplate = config.getStringRedisTemplate(cf);
        redisTemplate.opsForValue().set("b","qweqwe");
    }
}
