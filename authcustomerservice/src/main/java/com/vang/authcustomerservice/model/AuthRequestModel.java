package com.vang.authcustomerservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestModel implements Serializable {

    private String username;
    private String email;
    private String phone;
    private String password;
}