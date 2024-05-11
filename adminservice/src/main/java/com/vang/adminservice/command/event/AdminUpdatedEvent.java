package com.vang.adminservice.command.event;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class AdminUpdatedEvent {

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
    private LocalDateTime createddate;
    private Date dateofbirth;
    private Integer activestatus;
    private String avatar;
    private byte[] image;
    private String fileName;
}