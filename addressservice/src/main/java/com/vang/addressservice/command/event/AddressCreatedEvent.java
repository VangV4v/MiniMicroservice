package com.vang.addressservice.command.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class AddressCreatedEvent {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String addressid;
    private String customerid;
    private String addressdetail;
    private String phone;
    private String name;
    private String note;
    private String createddate;
    private String lastmodified;
}