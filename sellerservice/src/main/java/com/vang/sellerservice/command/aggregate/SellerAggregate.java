package com.vang.sellerservice.command.aggregate;

import com.vang.sellerservice.command.command.CreateSellerCommand;
import com.vang.sellerservice.command.command.DeleteSellerCommand;
import com.vang.sellerservice.command.command.UpdateSellerCommand;
import com.vang.sellerservice.command.event.SellerCreatedEvent;
import com.vang.sellerservice.command.event.SellerDeletedEvent;
import com.vang.sellerservice.command.event.SellerUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class SellerAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String sellerid;
    private String firstname;
    private String lastname;
    private String username;
    private String shopname;
    private String shopnameexpiration;
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

    public SellerAggregate() {}

    @CommandHandler
    public SellerAggregate(CreateSellerCommand command) {

        SellerCreatedEvent event = new SellerCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public SellerAggregate(UpdateSellerCommand command) {
        SellerUpdatedEvent event = new SellerUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public SellerAggregate(DeleteSellerCommand command) {
        SellerDeletedEvent event = new SellerDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(SellerCreatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.sellerid = event.getSellerid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.username = event.getUsername();
        this.shopname = event.getShopname();
        this.shopnameexpiration = event.getShopnameexpiration();
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
    }

    @EventSourcingHandler
    public void handle(SellerUpdatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.sellerid = event.getSellerid();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.username = event.getUsername();
        this.shopname = event.getShopname();
        this.shopnameexpiration = event.getShopnameexpiration();
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
    public void handle(SellerDeletedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.sellerid = event.getSellerid();
    }

}