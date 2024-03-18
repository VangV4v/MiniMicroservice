package com.vang.customerservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
public class CreateCustomerCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String customerid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String confirmcode;
    private LocalDateTime confirmcodeexpiration;
    private String phone;
    private String password;
    private String role;
    private LocalDateTime createddate;
    private LocalDateTime dateofbirth;
    private Integer activestatus;
    private String avatar;
}