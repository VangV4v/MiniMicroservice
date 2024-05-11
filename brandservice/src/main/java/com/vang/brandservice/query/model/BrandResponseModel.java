package com.vang.brandservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandResponseModel implements Serializable {

    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private int activestatus;
    private boolean dataStatus = true;
    public void initializeValue() {
        this.brandid = "";
        this.brandname = "";
        this.description = "";
        this.activestatus = 0;
        this.dataStatus = false;
    }
}