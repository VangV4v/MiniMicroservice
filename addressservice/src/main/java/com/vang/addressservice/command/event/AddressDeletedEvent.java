package com.vang.addressservice.command.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class AddressDeletedEvent {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String addressid;
}