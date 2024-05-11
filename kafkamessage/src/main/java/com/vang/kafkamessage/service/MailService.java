package com.vang.kafkamessage.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Autowired
    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void notifyRegistrationAccount(String to) {
        try {
            Context context = new Context();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            context.setVariable("email", to);
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            String htmlTemplate = templateEngine.process("registeraccount", context);
            message.setTo(to);
            message.setSubject("REGISTRATION SUCCESS");
            message.setText(htmlTemplate,true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {

        }
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            Context context = new Context();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            context.setVariable("email", to);
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            String htmlTemplate = templateEngine.process("registeraccount", context);
            message.setTo(to);
            message.setSubject("REGISTRATION SUCCESS");
            message.setText(htmlTemplate,true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {

        }


    }

}