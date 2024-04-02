package com.vang.adminservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Date;

@Data
public class UpdateAdminCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String adminid;
    private String firstname;
    private String lastname;
    private String email;
    private String confirmcode;
    private Date confirmcodeexpiration;
    private String phone;
    private String password;
    private String passwordsecret;
    private String role;
    private Date createddate;
    private Date dateofbirth;
    private Integer activestatus;
    private String avatar;
}