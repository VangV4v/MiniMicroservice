package com.vang.kafkamessage.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerMessageModel implements Serializable {

    private String fullName;
    private String email;
    private String username;
    private String phone;
}