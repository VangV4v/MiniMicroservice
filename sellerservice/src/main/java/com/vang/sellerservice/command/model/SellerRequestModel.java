package com.vang.sellerservice.command.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class SellerRequestModel implements Serializable {

    private String sellerid;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String shopname;
    @NotNull
    private String email;
    private String confirmcode;
    private Timestamp confirmcodeexpiration;
    @NotNull
    private String phone;
    @NotNull
    private String password;
    private String role;
    private String createddate;
    private String lastmodified;
    private String dateofbirth;
    private int activestatus;
    private String avatar;
}