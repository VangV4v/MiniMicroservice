package com.vang.customerservice.command.aggregate;

import com.vang.customerservice.command.command.CreateCustomerCommand;
import com.vang.customerservice.command.command.DeleteCustomerCommand;
import com.vang.customerservice.command.command.UpdateCustomerCommand;
import com.vang.customerservice.command.event.CustomerCreatedEvent;
import com.vang.customerservice.command.event.CustomerDeletedEvent;
import com.vang.customerservice.command.event.CustomerUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class CustomerAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String customerid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String confirmcode;
    private String confirmcodeexpiration;
    private String phone;
    private String password;
    private String role;
    private String createddate;
    private String lastmodified;
    private String dateofbirth;
    private int activestatus;
    private String avatar;
    private byte[] image;
    private String fileName;

    public CustomerAggregate() {}

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {

        CustomerCreatedEvent event = new CustomerCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CustomerAggregate(UpdateCustomerCommand command) {

        CustomerUpdatedEvent event = new CustomerUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CustomerAggregate(DeleteCustomerCommand command) {

        CustomerDeletedEvent event = new CustomerDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(CustomerCreatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.customerid = event.getCustomerid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.confirmcode = event.getConfirmcode();
        this.confirmcodeexpiration = event.getConfirmcodeexpiration();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.role = event.getRole();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
        this.dateofbirth = event.getDateofbirth();
    }

    @EventSourcingHandler
    public void handle(CustomerUpdatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.customerid = event.getCustomerid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.confirmcode = event.getConfirmcode();
        this.confirmcodeexpiration = event.getConfirmcodeexpiration();
        this.phone = event.getPhone();
        this.password = event.getPassword();
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
    public void handle(CustomerDeletedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.customerid = event.getCustomerid();
    }

}