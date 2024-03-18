package com.vang.authcustomerservice.data;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "customers")
@NamedStoredProcedureQuery(name = "authCustomer", procedureName = "authCustomer", parameters = {
        @StoredProcedureParameter(name = "authkey", type = String.class, mode = ParameterMode.IN)
})
@Data
public class Customers {

    @Id
    @Column(name = "customerid")
    private String customerid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
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
    @Column(name = "role")
    private String role;
    @Column(name = "createddate")
    private Date createddate;
    @Column(name = "dateofbirth")
    private Date dateofbirth;
    @Column(name = "activestatus")
    private Integer activestatus;
    @Column(name = "avatar")
    private String avatar;
}