package com.vang.cartservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteAllCartCommand {
    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String customerid;
}