package com.vang.kafkamessage.service;

import com.vang.kafkamessage.model.SellerMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final MailService mailService;

    @Autowired
    public SellerService(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(groupId = "registrationSeller", topics = "registrationSeller")
    public void handlerMessage(SellerMessageModel messageModel) {
        mailService.sendEmail(messageModel.getEmail(), "CREATE ACCOUNT", "Thanks.");
    }
}
