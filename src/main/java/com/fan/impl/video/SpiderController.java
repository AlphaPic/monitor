package com.fan.impl.video;

import com.fan.dao.interfaces.baseService.ISpiderThreadService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.SpiderLookUpRequest;
import com.fan.dao.model.response.SpiderLookUpResponse;
import com.fan.framework.annotation.MonitorController;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:fanwenlong
 * @date:2018-03-08 15:17:27
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:使用Jsoup进行网页的爬取和存储
 * @detail:
 */
@MonitorController
public class SpiderController {
    private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);

    @Autowired
    private ISpiderThreadService spiderThreadService;

    /**
     * 根据请求进行查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api.video.spider.lookUp/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    public AlphaResponse<SpiderLookUpResponse> lookUp(SpiderLookUpRequest request){
        AlphaResponse<SpiderLookUpResponse> response = new AlphaResponse<SpiderLookUpResponse>();
        SpiderLookUpResponse spiderLookUpResponse = new SpiderLookUpResponse();
        response.setDate(spiderLookUpResponse);

        String actor    = request.getActor();       /** 演员名称 */
        String country  = request.getCountry();     /** 国家 */
        String director = request.getDirector();    /** 导演 */
        String year     = request.getYear();        /** 年份 */
        String month    = request.getMonth();       /** 月份 */
        String name     = request.getName();        /** 电影名称 */


        return response;
    }
}
