package com.vang.adminservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Date;

@Data
public class DeleteAdminCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String adminid;
}