package com.vang.kafkamessage.model;

import lombok.Data;
@Data
public class CustomerMessageModel {

    private String fullName;
    private String email;
    private String username;
    private String phone;
}