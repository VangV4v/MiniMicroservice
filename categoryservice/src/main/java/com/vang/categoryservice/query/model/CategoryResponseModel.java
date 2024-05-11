package com.vang.categoryservice.query.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponseModel {

    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;
    private boolean dataStatus = true;
    public void initialize() {
        this.categoryid = "";
        this.categoryname = "";
        this.description = "";
        this.activestatus = 0;
        this.createddate = LocalDateTime.now();
        this.lastmodified = LocalDateTime.now();
        this.dataStatus = false;
    }
}