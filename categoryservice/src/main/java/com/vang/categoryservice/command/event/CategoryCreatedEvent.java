package com.vang.categoryservice.command.event;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryCreatedEvent {

    private Long autoAggregateIdentifier;
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;
}
