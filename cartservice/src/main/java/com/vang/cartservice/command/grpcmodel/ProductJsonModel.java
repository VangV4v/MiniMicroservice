package com.vang.cartservice.command.grpcmodel;

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
    private String defaultimage;
    private int activestatus;
}