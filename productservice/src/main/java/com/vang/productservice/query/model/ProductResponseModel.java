package com.vang.productservice.query.model;

import com.vang.productservice.command.grpcmodel.BrandJsonModel;
import com.vang.productservice.command.grpcmodel.CategoryJsonModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductResponseModel implements Serializable {
    private String productid;
    private String brandid;
    private BrandJsonModel branddetail;
    private String categoryid;
    private CategoryJsonModel categorydetail;
    private String sellerid;
    private String sellerdetail;
    private String productname;
    private String description;
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

    public void initialize() {
        productid = null;
        brandid = null;
        branddetail = null;
        categoryid = null;
        categorydetail = null;
        productname = null;
        description = null;
        price = null;
        quantity = 0;
        soldquantity = 0;
        createddate = null;
        lastmodified = null;
        defaultimage = null;
        image1 = null;
        image2 = null;
        image3 = null;
        image4 = null;
        image5 = null;
        image6 = null;
        image7 = null;
        image8 = null;
        image9 = null;
        image10 = null;
        activestatus = 0;
    }
}