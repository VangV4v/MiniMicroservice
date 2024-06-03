package com.vang.cartservice.command.event;

import lombok.Data;

@Data
public class AllCartsDeletedEvent {
    private Long autoAggregateIdentifier;
    private String customerid;
}