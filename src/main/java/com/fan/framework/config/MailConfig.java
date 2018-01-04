package com.fan.framework.config;

import com.fan.consts.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author:fanwenlong
 * @date:2018-01-02 14:24:53
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:JavaMail的邮件配置
 * @detail:配置JavaMail的服务
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MailConfig {
    private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);


    /**
     * 邮件发送者
     * @return
     */
    @Bean
    public JavaMailSenderImpl getMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(InitConfig.MAIL_QQ_SMTP_HOST);
        sender.setUsername(InitConfig.MAIL_QQ_SMTP_USERNAME);
        sender.setPassword(InitConfig.MAIL_QQ_SMTP_AUTHCODE);
        return sender;
    }

    /**
     * 获取消息的实例
     * @param sender
     * @return
     */
    @Bean
    public MimeMessage getMessage(JavaMailSenderImpl sender){
        return sender.createMimeMessage();
    }

    /**
     * 获取消息助手的实例
     * @return
     */
    @Bean
    public MimeMessageHelper getMessageHelper(MimeMessage message){
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            return helper;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws MessagingException {
        MailConfig demo = new MailConfig();
        JavaMailSenderImpl sender = demo.getMailSender();
        MimeMessage message = demo.getMessage(sender);
        MimeMessageHelper helper = demo.getMessageHelper(message);
        helper.setFrom(InitConfig.MAIL_QQ_SMTP_USERNAME);
        helper.setTo("m13168793059@163.com");
        helper.setText("hello");

        sender.send(message);
//        final String smtp_host = "smtp.qq.com";
//        final String smtp_username = "1693293713@qq.com";
//        final String smtp_password = "kwfitspfcxpmbaji";
//        final String smtp_connection = "SSL"; // Use ‘TLS’ or ‘SSL’ connection
//
//        final String fromEmail="1693293713@qq.com";
//        final String toEmail="m13168793059@163.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//
//
//        if (smtp_connection.equals("TLS")) {
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.host",smtp_host);
//        } else{
//            props.put("mail.smtp.host",smtp_host);
//            props.put("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.port", "465");
//        }
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(smtp_username, smtp_password);
//                    }
//                });
//
//        try {
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(fromEmail, "NoReply"));
//            msg.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(toEmail, "Mr. Recipient"));
//            msg.setSubject("Welcome To JavaMail API");
//            msg.setText("JavaMail API Test – Sending email example through remote smtp server");
//            Transport.send(msg);
//            System.out.println("Email sent successfully…");
//        } catch (AddressException e) {
//            throw new RuntimeException(e);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }
}
