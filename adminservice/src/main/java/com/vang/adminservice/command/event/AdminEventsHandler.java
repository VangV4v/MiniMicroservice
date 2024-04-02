package com.vang.adminservice.command.event;

import com.vang.adminservice.data.Admins;
import com.vang.adminservice.data.AdminsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminEventsHandler {

    private final AdminsRepository repository;

    @Autowired
    public AdminEventsHandler(AdminsRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(AdminCreatedEvent event) {

        String adminId = repository.autoGenerateIdAdmin();
        event.setAdminid(adminId);
        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        repository.save(admins);
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