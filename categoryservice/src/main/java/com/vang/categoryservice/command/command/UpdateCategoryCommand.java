package com.vang.categoryservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
public class UpdateCategoryCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;
}