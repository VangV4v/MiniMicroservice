package com.vang.brandservice.command.model;

import lombok.Data;

@Data
public class BrandRequestModel {

    private String brandid;
    private String brandname;
    private String description;
    private Integer activestatus;
}