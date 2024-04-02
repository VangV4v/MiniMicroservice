package com.vang.adminservice.command.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class AdminRequestModel implements Serializable {

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
