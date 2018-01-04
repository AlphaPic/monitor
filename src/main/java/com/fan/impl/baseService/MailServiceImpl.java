package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IMailService;
import com.fan.dao.model.basicService.AlphaMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.util.List;

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

    @Autowired
    private MimeMessage message;

    @Autowired
    private MimeMessageHelper messageHelper;

    @Autowired
    private JavaMailSenderImpl sender;

    /**
     * 往一个用户发送邮件
     * @param simpleMessage
     * @return
     */
    @Override
    public Boolean sendASingleMail(AlphaMessageInfo simpleMessage) {
        if(isMessageInfoIlleagle(simpleMessage) == false){
            return false;
        }
        try {
            messageHelper.setText(simpleMessage.getText());
            messageHelper.setTo(simpleMessage.getSingleDest());
            messageHelper.setFrom(simpleMessage.getFrom());
            sender.send(message);
        }catch (Exception e){
            logger.error("发送邮件失败");
            return false;
        }
        return true;
    }

    /**
     * 往多个用户发送邮件
     * @param multiMessage
     * @return
     */
    @Override
    public Boolean sendMultiMail(AlphaMessageInfo multiMessage) {
        if(isMessageInfoIlleagle(multiMessage) == false){
            return false;
        }
        try {
            List<String> destCollect = multiMessage.getMultiDest();
            String[] destArray = new String[destCollect.size()];
            Integer index = 0;
            for(String dest : destCollect){
                destArray[index++] = dest;
            }
            messageHelper.setText(multiMessage.getText());
            messageHelper.setTo(destArray);
            messageHelper.setFrom(multiMessage.getFrom());
            sender.send(message);
        }catch (Exception e){
            logger.error("发送邮件失败");
            return false;
        }
        return true;
    }

    /**
     * 判断邮箱账户是不是存在
     * @param email
     * @return
     */
    @Override
    public Boolean isMailAccountExists(String email) {
        return false;
    }

    /**
     * 判断邮件的相关信息是否合法
     * @param messageInfo
     * @return
     */
    private Boolean isMessageInfoIlleagle(AlphaMessageInfo messageInfo){
        if(messageInfo == null){
            logger.error("消息体为空");
            return false;
        }
        if(messageInfo.getMultiSender() == false){
            /** 发送给个人 */
            if(StringUtils.isEmpty(messageInfo.getSingleDest()) == true){
                logger.error("目标邮件用户不存在");
                return false;
            }
        }else {
            /** 发送给群体 */
            if(messageInfo.getMultiDest() == null || messageInfo.getMultiDest().isEmpty()){
                logger.error("目标邮件用户不存在");
                return false;
            }
        }
        if(StringUtils.isEmpty(messageInfo.getFrom()) == true || StringUtils.isEmpty(messageInfo.getText()) == true){
            logger.error("发送者为空或者消息内容为空");
            return false;
        }
        return true;
    }
}
