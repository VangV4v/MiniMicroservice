package com.vang.kafkamessage.service;

import com.vang.kafkamessage.model.CustomerMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private MailService mailService;

    @KafkaListener(groupId = "sendmailcreatecustomer", topics = "sendmailcreatecustomer")
    public void handlerMessage(CustomerMessageModel message) {

        mailService.sendEmail(message.getEmail(), "CREATE ACCOUNT", "Thanks.");
        System.out.println("-------------------Success------------------");
    }
}