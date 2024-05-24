package com.vang.productservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String productid;
}