package com.vang.customerservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Date;

@Data
public class DeleteCustomerCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String customerid;
}