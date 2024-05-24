package com.vang.sellerservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteSellerCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String sellerid;
}