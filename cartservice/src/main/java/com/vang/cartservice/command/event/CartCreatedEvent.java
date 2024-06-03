package com.vang.cartservice.command.event;

import lombok.Data;

@Data
public class CartCreatedEvent {
    private Long autoAggregateIdentifier;
    private String cartid;
    private String customerid;
    private String productid;
    private String productdetail;
    private int quantity;
}