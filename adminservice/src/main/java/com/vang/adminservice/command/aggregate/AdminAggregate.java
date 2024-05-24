package com.vang.adminservice.command.aggregate;

import com.vang.adminservice.command.command.CreateAdminCommand;
import com.vang.adminservice.command.command.DeleteAdminCommand;
import com.vang.adminservice.command.command.UpdateAdminCommand;
import com.vang.adminservice.command.event.AdminCreatedEvent;
import com.vang.adminservice.command.event.AdminDeletedEvent;
import com.vang.adminservice.command.event.AdminUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class AdminAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String adminid;
    private String firstname;
    private String lastname;
    private String email;
    private String confirmcode;
    private String confirmcodeexpiration;
    private String phone;
    private String password;
    private String passwordsecret;
    private String role;
    private String createddate;
    private String lastmodified;
    private String dateofbirth;
    private int activestatus;
    private String avatar;
    private byte[] image;
    private String fileName;

    public AdminAggregate() {}

    @CommandHandler
    public AdminAggregate(CreateAdminCommand command) {

        AdminCreatedEvent event = new AdminCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(UpdateAdminCommand command) {

        AdminUpdatedEvent event = new AdminUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(DeleteAdminCommand command) {

        AdminDeletedEvent event = new AdminDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AdminCreatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.adminid = event.getAdminid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.email = event.getEmail();
        this.confirmcode = event.getConfirmcode();
        this.confirmcodeexpiration = event.getConfirmcodeexpiration();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.passwordsecret = event.getPasswordsecret();
        this.role = event.getRole();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
        this.dateofbirth = event.getDateofbirth();
        this.activestatus = event.getActivestatus();
        this.avatar = event.getAvatar();
    }

    @EventSourcingHandler
    public void handle(AdminUpdatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.adminid = event.getAdminid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.email = event.getEmail();
        this.confirmcode = event.getConfirmcode();
        this.confirmcodeexpiration = event.getConfirmcodeexpiration();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.passwordsecret = event.getPasswordsecret();
        this.role = event.getRole();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
        this.dateofbirth = event.getDateofbirth();
        this.activestatus = event.getActivestatus();
        this.avatar = event.getAvatar();
        this.image = event.getImage();
        this.fileName = event.getFileName();
    }

    @EventSourcingHandler
    public void handle(AdminDeletedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.adminid = event.getAdminid();

    }
}