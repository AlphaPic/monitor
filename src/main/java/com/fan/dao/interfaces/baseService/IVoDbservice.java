package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.basicService.VoInfo;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:03:27
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo的数据库服务
 * @detail:
 */
public interface IVoDbservice {
    /**
     * 插入vo的值
     * @param voInfo
     * @return
     */
    public Boolean InsertVoInfoToDb(VoInfo voInfo);
}
