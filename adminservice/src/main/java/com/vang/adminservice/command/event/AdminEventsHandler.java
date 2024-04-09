package com.vang.adminservice.command.event;

import com.vang.adminservice.command.model.AdminMessageModel;
import com.vang.adminservice.data.Admins;
import com.vang.adminservice.data.AdminsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminEventsHandler {

    private final AdminsRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public AdminEventsHandler(AdminsRepository repository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventHandler
    public void handle(AdminCreatedEvent event) {

        String adminId = repository.autoGenerateIdAdmin();
        event.setAdminid(adminId);
        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        repository.save(admins);
        AdminMessageModel messageModel = new AdminMessageModel();
        messageModel.setEmail(event.getEmail());
        messageModel.setPhone(event.getPhone());
        messageModel.setFullName(event.getFirstname() + event.getLastname());
        kafkaTemplate.send("sendmailcreateadmin", messageModel);
    }

    @EventHandler
    public void handle(AdminUpdatedEvent event) {

        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        repository.save(admins);
    }

    @EventHandler
    public void handle(AdminDeletedEvent event) {

        repository.deleteById(event.getAdminid());
    }
}