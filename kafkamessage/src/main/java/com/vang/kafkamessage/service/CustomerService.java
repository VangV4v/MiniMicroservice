package com.vang.kafkamessage.service;

import com.vang.kafkamessage.model.CustomerMessageModel;
import com.vang.kafkamessage.model.NotifyRegistrationAccountMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final MailService mailService;

    @Autowired
    public CustomerService(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(groupId = "notifyRegistrationCustomer", topics = "notifyRegistrationCustomer")
    public void handleNotifyRegistrationCustomer(NotifyRegistrationAccountMessage message) {
        mailService.notifyRegistrationAccount(message.getEmail());
    }

}