package com.vang.kafkamessage.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminMessageModel implements Serializable {

    private String fullName;
    private String email;
    private String phone;
}