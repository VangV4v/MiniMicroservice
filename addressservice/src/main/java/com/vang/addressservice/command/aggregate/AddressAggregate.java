package com.vang.addressservice.command.aggregate;

import com.vang.addressservice.command.command.CreateAddressCommand;
import com.vang.addressservice.command.command.DeleteAddressCommand;
import com.vang.addressservice.command.command.UpdateAddressCommand;
import com.vang.addressservice.command.event.AddressCreatedEvent;
import com.vang.addressservice.command.event.AddressDeletedEvent;
import com.vang.addressservice.command.event.AddressUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class AddressAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String addressid;
    private String customerid;
    private String addressdetail;
    private String phone;
    private String name;
    private String note;
    private String createddate;
    private String lastmodified;

    public AddressAggregate() {}

    @CommandHandler
    public AddressAggregate(CreateAddressCommand command) {
        AddressCreatedEvent event = new AddressCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AddressAggregate(UpdateAddressCommand command) {
        AddressUpdatedEvent event = new AddressUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AddressAggregate(DeleteAddressCommand command) {
        AddressDeletedEvent event = new AddressDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AddressCreatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.addressid = event.getAddressid();
        this.customerid = event.getCustomerid();
        this.addressdetail = event.getAddressdetail();
        this.phone = event.getPhone();
        this.name = event.getName();
        this.note = event.getNote();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
    }

    @EventSourcingHandler
    public void handle(AddressUpdatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.addressid = event.getAddressid();
        this.customerid = event.getCustomerid();
        this.addressdetail = event.getAddressdetail();
        this.phone = event.getPhone();
        this.name = event.getName();
        this.note = event.getNote();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
    }

    @EventSourcingHandler
    public void handle(AddressDeletedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.addressid = event.getAddressid();
    }
}