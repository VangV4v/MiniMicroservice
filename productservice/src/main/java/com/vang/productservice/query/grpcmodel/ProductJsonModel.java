package com.vang.productservice.query.grpcmodel;

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
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;
    private String image9;
    private String image10;
    private int activestatus;
}