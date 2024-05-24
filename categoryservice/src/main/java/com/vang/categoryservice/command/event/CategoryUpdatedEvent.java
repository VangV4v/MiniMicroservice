package com.vang.categoryservice.command.event;

import lombok.Data;

@Data
public class CategoryUpdatedEvent {

    private Long autoAggregateIdentifier;
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;
}