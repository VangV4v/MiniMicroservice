package com.vang.categoryservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryRequestModel implements Serializable {
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;
}