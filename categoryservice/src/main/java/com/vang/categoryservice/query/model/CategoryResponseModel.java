package com.vang.categoryservice.query.model;

import lombok.Data;

@Data
public class CategoryResponseModel {

    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;
    private boolean dataStatus = true;
    public void initialize() {
        this.categoryid = "";
        this.categoryname = "";
        this.description = "";
        this.activestatus = 0;
        this.createddate = null;
        this.lastmodified = null;
        this.dataStatus = false;
    }
}