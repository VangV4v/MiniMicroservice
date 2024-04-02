package com.vang.brandservice.command.event;

import lombok.Data;

@Data
public class BrandCreatedEvent {

    private Long autoAggregateIdentifier;
    private String brandid;
    private String brandname;
    private String description;
    private int activestatus;
}