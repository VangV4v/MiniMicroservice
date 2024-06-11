package com.vang.addressservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteAddressCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String addressid;
}