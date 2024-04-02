package com.vang.brandservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateBrandCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String brandid;
    private String brandname;
    private String description;
    private int activestatus;
}
