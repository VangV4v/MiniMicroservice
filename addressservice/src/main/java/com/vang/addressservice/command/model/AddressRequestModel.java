package com.vang.addressservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressRequestModel implements Serializable {

    private String addressid;
    private String customerid;
    private String addressdetail;
    private String phone;
    private String name;
    private String note;
    private String createddate;
    private String lastmodified;
}