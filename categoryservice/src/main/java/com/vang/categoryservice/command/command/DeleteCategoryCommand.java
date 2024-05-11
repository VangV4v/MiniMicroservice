package com.vang.categoryservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteCategoryCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String categoryid;
}