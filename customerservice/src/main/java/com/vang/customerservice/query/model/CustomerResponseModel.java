package com.vang.customerservice.query.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CustomerResponseModel implements Serializable {

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
        dateofbirth = null;
        activestatus = 0;
        avatar = "";
        dataStatus = false;
    }
}