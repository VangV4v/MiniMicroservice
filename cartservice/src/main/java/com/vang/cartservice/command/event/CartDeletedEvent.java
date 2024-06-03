package com.vang.cartservice.command.event;

import lombok.Data;

@Data
public class CartDeletedEvent {
    private Long autoAggregateIdentifier;
    private String cartid;
}