package com.vang.adminservice.command.event;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class AdminUpdatedEvent {

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
    private byte[] image;
    private String fileName;
}