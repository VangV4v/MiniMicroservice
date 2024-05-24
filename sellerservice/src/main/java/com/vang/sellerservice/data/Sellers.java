package com.vang.sellerservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sellers")
@Data
public class Sellers {

    @Id
    @Column(name = "sellerid")
    private String sellerid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "shopname")
    private String shopname;
    @Column(name = "shopnameexpiration")
    private String shopnameexpiration;
    @Column(name = "email")
    private String email;
    @Column(name = "confirmcode")
    private String confirmcode;
    @Column(name = "confirmcodeexpiration")
    private String confirmcodeexpiration;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "lastmodified")
    private String lastmodified;
    @Column(name = "dateofbirth")
    private String dateofbirth;
    @Column(name = "activestatus")
    private int activestatus;
    @Column(name = "avatar")
    private String avatar;
}