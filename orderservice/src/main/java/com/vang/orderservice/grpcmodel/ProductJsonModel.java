package com.vang.orderservice.grpcmodel;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductJsonModel implements Serializable {

    private String productid;
    private String brandid;
    private String branddetail;
    private String categoryid;
    private String categorydetail;
    private String productname;
    private String description;
    private String sellerid;
    private String sellerdetail;
    private BigDecimal price;
    private int quantity;
    private int soldquantity;
    private String createddate;
    private String lastmodified;
    private String defaultimage;
    private int activestatus;
}