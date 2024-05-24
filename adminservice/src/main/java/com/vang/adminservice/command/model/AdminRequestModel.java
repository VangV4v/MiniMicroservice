package com.vang.adminservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRequestModel implements Serializable {

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