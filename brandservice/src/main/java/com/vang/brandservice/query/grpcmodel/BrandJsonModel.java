package com.vang.brandservice.query.grpcmodel;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BrandJsonModel implements Serializable {

    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private String createddate;
    private String lastmodified;
    private int activestatus;
}