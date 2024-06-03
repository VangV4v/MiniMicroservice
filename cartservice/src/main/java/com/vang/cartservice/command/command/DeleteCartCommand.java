package com.vang.cartservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteCartCommand {
    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String cartid;
}
