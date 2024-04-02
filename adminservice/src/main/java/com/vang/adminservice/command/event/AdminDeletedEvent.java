package com.vang.adminservice.command.event;

import lombok.Data;

import java.sql.Date;

@Data
public class AdminDeletedEvent {

    private Long autoAggregateIdentifier;
    private String adminid;
}