package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IMailService;
import com.fan.dao.model.basicService.AlphaMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author:fanwenlong
 * @date:2018-01-04 15:11:05
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:邮件服务的提供者
 * @detail:
 */
@Component
public class MailServiceImpl implements IMailService{
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * 往一个用户发送邮件
     * @param message
     * @return
     */
    @Override
    public Boolean sendASingleMail(AlphaMessageInfo message) {
        return null;
    }

    /**
     * 往多个用户发送邮件
     * @param message
     * @return
     */
    @Override
    public Integer sendMultiMail(AlphaMessageInfo message) {
        return null;
    }

    /**
     * 判断邮箱账户是不是存在
     * @param email
     * @return
     */
    @Override
    public Boolean isMailAccountExists(String email) {
        return null;
    }
}
