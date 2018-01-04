package com.fan.impl.monitor;

import com.fan.dao.interfaces.monitor.MessageQueueStatusDao;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.MessageQueueStatusRequest;
import com.fan.dao.model.response.MessageQueueStatusResponse;
import com.fan.framework.annotation.MonitorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author:fanwenlong
 * @date:2017-12-10 17:08:45
 * @E-mail:fanwenlong@lvmama.com
 * @mobile:186-0307-4401
 * @description:
 */
@MonitorController
public class MessageQueueStatusImpl implements MessageQueueStatusDao{

    /**
     * 消息队列状态相关的接口
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/status/1.0.0")
    @Override
    public AlphaResponse<MessageQueueStatusResponse> getMessageQueueStatusInfo(MessageQueueStatusRequest request) {

        MessageQueueStatusResponse responseBody = new MessageQueueStatusResponse();
        AlphaResponse<MessageQueueStatusResponse> response = new AlphaResponse<MessageQueueStatusResponse>();
        response.setDate(responseBody);
        response.setCode("2");
        response.setMessage("队列数目已拿到");
        responseBody.setQueueNum(12);
        return response;
    }
}
