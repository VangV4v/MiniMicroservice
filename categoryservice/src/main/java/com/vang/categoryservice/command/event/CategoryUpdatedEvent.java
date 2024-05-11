package com.vang.categoryservice.command.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryUpdatedEvent {

    private Long autoAggregateIdentifier;
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;
}