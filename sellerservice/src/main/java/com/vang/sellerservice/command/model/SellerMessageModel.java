package com.vang.sellerservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SellerMessageModel implements Serializable {

    private String fullName;
    private String email;
    private String username;
    private String phone;
}