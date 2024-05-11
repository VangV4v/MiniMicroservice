package com.vang.categoryservice.command.event;

import lombok.Data;

@Data
public class CategoryDeletedEvent {

    private Long autoAggregateIdentifier;
    private String categoryid;
}