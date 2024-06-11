package com.vang.addressservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressResponseModel implements Serializable {
    private String addressid;
    private String customerid;
    private String addressdetail;
    private String phone;
    private String name;
    private String note;
    private String createddate;
    private String lastmodified;
}