package com.vang.cartservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateCartCommand {
    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String cartid;
    private String customerid;
    private String productid;
    private String productdetail;
    private int quantity;
}