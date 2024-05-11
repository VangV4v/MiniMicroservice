package com.vang.adminservice.data;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@NamedStoredProcedureQuery(name = "autoIdAdmin", procedureName = "autoIdAdmin", parameters = {
        @StoredProcedureParameter(name = "adminid", type = String.class, mode = ParameterMode.OUT)
})
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
    private Date confirmcodeexpiration;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "passwordsecret")
    private String passwordsecret;
    @Column(name = "role")
    private String role;
    @Column(name = "createddate")
    private LocalDateTime createddate;
    @Column(name = "dateofbirth")
    private Date dateofbirth;
    @Column(name = "activestatus")
    private Integer activestatus;
    @Column(name = "avatar")
    private String avatar;
}