package com.vang.categoryservice.query.grpcmodel;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CategoryJsonModel implements Serializable {

    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;
}