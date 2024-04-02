package com.vang.brandservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandRequestModel implements Serializable {

    private String brandid;
    private String brandname;
    private String description;
    private int activestatus;
}