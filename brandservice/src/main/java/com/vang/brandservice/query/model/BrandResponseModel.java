package com.vang.brandservice.query.model;

import lombok.Data;

@Data
public class BrandResponseModel {

    private String brandid;
    private String brandname;
    private String description;
    private Integer activestatus;
}