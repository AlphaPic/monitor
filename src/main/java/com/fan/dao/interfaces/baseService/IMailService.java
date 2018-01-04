package com.fan.dao.interfaces.baseService;

import com.fan.dao.model.basicService.AlphaMessageInfo;

import javax.mail.internet.MimeMessage;

/**
 * @author:fanwenlong
 * @date:2018-01-04 15:07:40
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:邮件服务的提供者接口
 * @detail:
 */
public interface IMailService {
    /**
     * 往一个用户发送邮件
     * @param message
     * @return
     */
    public Boolean sendASingleMail(AlphaMessageInfo message);

    /**
     * 往多个用户发送邮件
     * @param message
     * @return
     */
    public Integer sendMultiMail(AlphaMessageInfo message);

    /**
     * 判断邮箱是不是存在
     * @param email
     * @return
     */
    public Boolean isMailAccountExists(String email);
}
