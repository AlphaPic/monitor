package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.request.SpiderLookUpRequest;

/**
 * @author:fanwenlong
 * @date:2018-03-08 17:54:46
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:爬虫服务接口
 * @detail:
 */
public interface ISpiderThreadService {

    /**
     * 添加搜索
     * @param request
     */
    public void addSearchElem(SpiderLookUpRequest request);
}
