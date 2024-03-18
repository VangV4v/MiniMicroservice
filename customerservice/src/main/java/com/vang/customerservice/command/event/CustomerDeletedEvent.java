package com.vang.customerservice.command.event;

import lombok.Data;

import java.sql.Date;

@Data
public class CustomerDeletedEvent {

    private Long autoAggregateIdentifier;
    private String customerid;
}