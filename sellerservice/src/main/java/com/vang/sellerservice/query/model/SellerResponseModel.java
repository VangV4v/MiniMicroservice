package com.vang.sellerservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SellerResponseModel implements Serializable {
    private String sellerid;
    private String firstname;
    private String lastname;
    private String username;
    private String shopname;
    private String shopnameexpiration;
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
    public void initialize() {
        this.sellerid = "";
        this.firstname = "";
        this.lastname = "";
        this.username = "";
        this.shopname = "";
        this.shopnameexpiration = "";
        this.email = "";
        this.confirmcode = "";
        this.confirmcodeexpiration = null;
        this.phone = "";
        this.password = "";
        this.role = "";
        this.createddate = null;
        this.lastmodified = null;
        this.dateofbirth = null;
        this.activestatus = 0;
        this.avatar = "";
        this.dataStatus = false;
    }
}