package com.fan.framework.config;

import com.fan.consts.InitConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author:fanwenlong
 * @date:2017-12-29 15:23:23
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:kafka的相关配置
 * @detail:用户发送消息
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class KafkaConfig {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

//    /** 设置生产者的属性 */
//    @Bean(name = "ProducerProperties")
//    public Properties getProducerProperties(){
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", InitConfig.KAFKA_COMMON_BOOTSTRAP_SERVER);
//        properties.setProperty("acks",InitConfig.KAFKA_PRODUCER_ACKS);
//        properties.setProperty("topic",InitConfig.KAFKA_COMMON_TOPIC);
//        properties.setProperty("retries", String.valueOf(InitConfig.KAFKA_PRODUCER_RETRIES));
//        properties.setProperty("batch.size", String.valueOf(InitConfig.KAFKA_PRODUCER_BATCH_SIZE));
//        properties.setProperty("linger.ms", String.valueOf(InitConfig.KAFKA_PRODUCER_LINGER_MS));
//        properties.setProperty("buffer.memory", String.valueOf(InitConfig.KAFKA_PRODUCER_BUFFER_MEMORY));
//        properties.setProperty("key.serializer",InitConfig.KAFKA_PRODUCER_KEY_SERIALIZER);
//        properties.setProperty("value.serializer",InitConfig.KAFKA_PRODUCER_VALUE_SERIALIZER);
//        return properties;
//    }
//
//    /** 产生消息生产者的bean */
//    @Bean
//    public KafkaProducer<String,String> getKafkaProducer(@Qualifier("ProducerProperties") Properties properties){
//        KafkaProducer producer = null;
//        try {
//            producer = new KafkaProducer(properties);
//        }catch (Exception e){
//            logger.error("创建生产者失败" + e.getMessage());
//        }
//        return producer;
//    }
//
//    /** 消费者属性设置 */
//    @Bean(name = "ConsumerProperties")
//    public Properties getConsumerProperties(){
//        Properties properties = new Properties();
//        properties.setProperty("topic",InitConfig.KAFKA_COMMON_TOPIC);
//        properties.setProperty("bootstrap.servers",InitConfig.KAFKA_COMMON_BOOTSTRAP_SERVER);
//        properties.setProperty("group.id",InitConfig.KAFKA_CONSUMER_GROUP_ID);
//        properties.setProperty("enable.auto.commit",InitConfig.KAFKA_CONSUMER_ENABLE_AUTO_COMMIT);
//        properties.setProperty("auto.commit.interval.ms",InitConfig.KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL_MS);
//        properties.setProperty("key.deserializer",InitConfig.KAFKA_CONSUMER_KEY_DESERIALIZER);
//        properties.setProperty("value.deserializer",InitConfig.KAFKA_CONSUMER_VALUE_DESERIALIZER);
//        return properties;
//    }
//
//    /** 消费消息的消费者 */
//    @Bean
//    public KafkaConsumer<String,String> getKafkaConsumer(@Qualifier("ConsumerProperties") Properties properties){
//        KafkaConsumer consumer = null;
//        try {
//            consumer = new KafkaConsumer(properties);
//            consumer.subscribe(Arrays.asList(InitConfig.KAFKA_COMMON_TOPIC));
//        }catch (Exception e){
//            logger.error("产生消费者失败" + e.getMessage());
//        }
//        return consumer;
//    }
}
