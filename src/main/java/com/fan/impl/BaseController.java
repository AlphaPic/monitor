package com.fan.impl;

import com.fan.framework.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
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

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "/index";
    }

    @ResponseBody
    @RequestMapping(value = "/api.base.test/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
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
        System.out.println(redisTemplate.opsForValue().get("a"));
//        System.out.println(cf.getConvertPipelineAndTxResults());
        return map;
    }
}
