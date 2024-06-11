package com.vang.customerservice.query.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerJsonModel implements Serializable {
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
}