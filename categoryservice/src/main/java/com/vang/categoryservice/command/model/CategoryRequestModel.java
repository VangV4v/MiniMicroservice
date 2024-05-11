package com.vang.categoryservice.command.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CategoryRequestModel implements Serializable {
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private LocalDateTime createddate;
    private LocalDateTime lastmodified;
}