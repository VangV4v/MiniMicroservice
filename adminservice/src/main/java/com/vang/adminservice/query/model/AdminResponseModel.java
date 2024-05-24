package com.vang.adminservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminResponseModel implements Serializable {

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
    private boolean dataStatus = true;

    public void initDefaultValue() {

        firstname = "";
        lastname = "";
        email = "";
        confirmcode = "";
        confirmcodeexpiration = null;
        phone = "";
        password = "";
        passwordsecret = "";
        role = "";
        createddate = null;
        lastmodified = null;
        dateofbirth = null;
        activestatus = 0;
        avatar = "";
        dataStatus = false;
    }
}
