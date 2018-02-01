package com.fan.dao.interfaces.monitor;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.RefreshVODBRequest;
import com.fan.dao.model.response.RefreshVODBResponse;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:29:17
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:基础的数据库服务
 * @detail:
 */
public interface IBasicDBService {

    /**
     * 刷新DB中的vo服务
     * @return
     */
    public AlphaResponse<RefreshVODBResponse> refreshDBVoInfo(RefreshVODBRequest request);
}
