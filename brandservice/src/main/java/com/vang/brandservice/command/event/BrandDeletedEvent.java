package com.vang.brandservice.command.event;

import lombok.Data;

@Data
public class BrandDeletedEvent {

    private Long autoAggregateIdentifier;
    private String brandid;
}