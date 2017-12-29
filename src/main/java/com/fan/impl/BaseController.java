package com.fan.impl;

import com.fan.consts.AuthEnum;
import com.fan.consts.InitConfig;
import com.fan.dao.interfaces.baseService.mapper.IUserMapper;
import com.fan.framework.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author:fanwenlong
 * @date:2017-12-02 13:50:50
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:基本的控制器
 */
@MonitorController
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ZooKeeper zk;

    @Autowired
    private KafkaProducer<String,String> producer;

    @Autowired
    private KafkaConsumer<String,String> consumer;

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/api.base.test/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.FORCE)
    public Map<String,Object> json(@RequestParam(value = "name" ,defaultValue = "user")String name){
        Map<String,Object> map = null;
        try {
            String tempName = new String(name);
            System.out.println(tempName);

            String userName = "Hello " + tempName;

            map = new HashMap<String, Object>();
            map.put("userName", userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(redisTemplate.opsForValue().get("a"));
//        System.out.println(redisTemplate.opsForValue().get("a"));
//        System.out.println(cf.getConvertPipelineAndTxResults());
//        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
//        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
//        System.out.println(mapper.getUserInfoByUserId(1));
//        logger.info(String.valueOf(mapper.getUserInfo(1)));
//        logger.info(String.valueOf(zk.getState().isAlive()));
//        logger.info("----------------");
//        for(int i = 0;i < 10;i++) {
//            producer.send(new ProducerRecord<String, String>("key" + i,"value" + i));
//        }
//        producer.close();
//        ConsumerRecords<String,String> consumerRecords = consumer.poll(InitConfig.KAFKA_CONSUMER_RECORDS_TIMEOUT);
//        Iterator iterator = consumerRecords.iterator();
//        while (iterator.hasNext()){
//            ConsumerRecord record = (ConsumerRecord) iterator.next();
//            System.out.println(record.key() + ":" + record.value());
//        }
//        logger.info("----------------");

        return map;
    }
}
