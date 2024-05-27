package com.vang.productservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String productid;
    private String brandid;
    private String branddetail;
    private String categoryid;
    private String categorydetail;
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
    private byte[] defaultImageByte;
    private String image1;
    private byte[] image1Byte;
    private String image2;
    private byte[] image2Byte;
    private String image3;
    private byte[] image3Byte;
    private String image4;
    private byte[] image4Byte;
    private String image5;
    private byte[] image5Byte;
    private String image6;
    private byte[] image6Byte;
    private String image7;
    private byte[] image7Byte;
    private String image8;
    private byte[] image8Byte;
    private String image9;
    private byte[] image9Byte;
    private String image10;
    private byte[] image10Byte;
    private int activestatus;
}