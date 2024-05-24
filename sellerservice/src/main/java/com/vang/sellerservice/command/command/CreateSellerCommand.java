package com.vang.sellerservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateSellerCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String sellerid;
    private String firstname;
    private String lastname;
    private String username;
    private String shopname;
    private String shopnameexpiration;
    private String email;
    private String confirmcode;
    private String confirmcodeexpiration;
    private String phone;
    private String password;
    private String role;
    private String createddate;
    private String lastmodified;
    private String dateofbirth;
    private int activestatus;
    private String avatar;
}