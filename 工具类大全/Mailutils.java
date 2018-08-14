package com.zgqb.loan.coretask.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by schongking on 2017/8/22.
 */
@Slf4j
@Component
public class MailUtil implements ApplicationContextAware{

    @Autowired
    static FreeMarkerConfigurer freeMarkerConfigurer;

    static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
        freeMarkerConfigurer = applicationContext.getBean(FreeMarkerConfigurer.class);
    }


    public static void sendTextMail(JavaMailSender sender,String subject,String from, String[] tos, String[] ccs, String[] bccs, String context){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(context);
        mailMessage.setFrom(from);
        mailMessage.setTo(tos);
        mailMessage.setCc(ccs);
        mailMessage.setBcc(bccs);
        sender.send(mailMessage);
    }

    public static void sendHtmlMail(JavaMailSender sender,String subject,String from, String[] tos, String[] ccs, String[] bccs, String context){
        MimeMessage mailMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
        try {
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(context, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(tos);
            if(null != ccs && ccs.length > 0) {
                mimeMessageHelper.setCc(ccs);
            }
            if(null != bccs && bccs.length > 0) {
                mimeMessageHelper.setBcc(bccs);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(mailMessage);
    }

    public static void sendFMTemplateMail(JavaMailSender sender, String subject, String from, String[] tos, String[] ccs, String[] bccs, Map data, String templateName){
        String htmlText = "";
        try {
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate(templateName);
            htmlText= FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        if(!StringUtil.isBlank(htmlText)) {
            sendHtmlMail(sender, subject, from, tos, ccs, bccs, htmlText);
        }else{
            log.error(String.format("邮件[%s]发送失败,收件人:", subject)+Arrays.asList(tos));
        }
    }
}
