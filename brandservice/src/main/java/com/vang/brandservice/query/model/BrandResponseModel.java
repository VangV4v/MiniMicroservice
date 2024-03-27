package com.vang.brandservice.query.model;

import lombok.Data;

@Data
public class BrandResponseModel {

    private String brandid;
    private String brandname;
    private String description;
    private Integer activestatus;
    private boolean dataStatus = true;
    public void initializeValue() {
        this.brandid = "";
        this.brandname = "";
        this.description = "";
        this.activestatus = 0;
        this.dataStatus = false;
    }
}