package com.vang.kafkamessage.service;

import com.vang.kafkamessage.model.AdminMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

   private final MailService mailService;

   @Autowired
   public AdminService(MailService mailService) {
       this.mailService = mailService;
   }

   @KafkaListener(groupId = "sendmailcreateadmin", topics = "sendmailcreateadmin")
   public void handlerMessage(AdminMessageModel messageModel) {
       mailService.sendEmail(messageModel.getEmail(), "CREATE ACCOUNT", "Thanks.");
   }
}