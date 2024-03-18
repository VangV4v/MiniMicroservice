package com.vang.authcustomerservice.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CustomerModel implements Serializable {

    private String customerid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String confirmcode;
    private Date confirmcodeexpiration;
    private String phone;
    private String password;
    private String role;
    private Date createddate;
    private Date dateofbirth;
    private Integer activestatus;
    private String avatar;
}