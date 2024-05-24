package com.vang.customerservice.command.event;

import lombok.Data;

@Data
public class CustomerUpdatedEvent {

    private Long autoAggregateIdentifier;
    private String customerid;
    private String firstname;
    private String lastname;
    private String username;
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
    private byte[] image;
    private String fileName;
}