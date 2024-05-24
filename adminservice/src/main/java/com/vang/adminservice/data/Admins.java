package com.vang.adminservice.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admins {

    @Id
    @Column(name = "adminid")
    private String adminid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
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
    @Column(name = "passwordsecret")
    private String passwordsecret;
    @Column(name = "role")
    private String role;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "lastmodified")
    private String lastmodified;
    @Column(name = "dateofbirth")
    private String dateofbirth;
    @Column(name = "activestatus")
    private Integer activestatus;
    @Column(name = "avatar")
    private String avatar;
}