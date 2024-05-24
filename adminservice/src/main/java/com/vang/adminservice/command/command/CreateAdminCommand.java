package com.vang.adminservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateAdminCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String adminid;
    private String firstname;
    private String lastname;
    private String email;
    private String confirmcode;
    private String confirmcodeexpiration;
    private String phone;
    private String password;
    private String passwordsecret;
    private String role;
    private String createddate;
    private String lastmodified;
    private String dateofbirth;
    private int activestatus;
    private String avatar;
}