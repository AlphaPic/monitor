package com.fan.dao.interfaces.monitor;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.MessageQueueStatusRequest;
import com.fan.dao.model.response.MessageQueueStatusResponse;

/**
 * @author:fanwenlong
 * @date:2017-12-10 16:58:59
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:消息状态接口
 */
public interface MessageQueueStatusDao {
    /**
     * 获取消息队列状态的方法
     * @param request
     * @return
     */
    public AlphaResponse<MessageQueueStatusResponse> getMessageQueueStatusInfo(MessageQueueStatusRequest request);
}
