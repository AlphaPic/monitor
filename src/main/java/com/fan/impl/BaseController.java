package com.fan.impl;

import com.fan.framework.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "user",method = {RequestMethod.GET,RequestMethod.POST})
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
        return map;
    }
}
