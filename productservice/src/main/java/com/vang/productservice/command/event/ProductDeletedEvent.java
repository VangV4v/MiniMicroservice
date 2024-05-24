package com.vang.productservice.command.event;

import lombok.Data;

@Data
public class ProductDeletedEvent {

    private Long autoAggregateIdentifier;
    private String productid;
}