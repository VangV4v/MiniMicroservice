package com.vang.brandservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteBrandCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String brandid;
}