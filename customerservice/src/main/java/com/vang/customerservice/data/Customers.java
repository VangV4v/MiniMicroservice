package com.vang.customerservice.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@NamedStoredProcedureQuery(name = "autoCustomerId", procedureName = "autoCustomerId", parameters = {
        @StoredProcedureParameter(name = "customerId", mode = ParameterMode.OUT, type = String.class)
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