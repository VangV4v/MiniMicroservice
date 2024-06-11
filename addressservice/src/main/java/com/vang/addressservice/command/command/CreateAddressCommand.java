package com.vang.addressservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateAddressCommand {

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