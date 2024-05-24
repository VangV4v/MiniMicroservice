package com.vang.customerservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerResponseModel implements Serializable {

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
    private boolean dataStatus = true;
    public void initDefaultValue() {
        customerid = "";
        firstname = "";
        lastname = "";
        username = "";
        email = "";
        confirmcode = "";
        confirmcodeexpiration = null;
        phone = "";
        password = "";
        role = "";
        createddate = null;
        lastmodified = null;
        dateofbirth = null;
        activestatus = 0;
        avatar = "";
        dataStatus = false;
    }
}