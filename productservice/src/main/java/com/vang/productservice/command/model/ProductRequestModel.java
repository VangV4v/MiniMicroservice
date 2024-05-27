package com.vang.productservice.command.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductRequestModel implements Serializable {

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
    private MultipartFile defaultImageByte;
    private MultipartFile image1Byte;
    private MultipartFile image2Byte;
    private MultipartFile image3Byte;
    private MultipartFile image4Byte;
    private MultipartFile image5Byte;
    private MultipartFile image6Byte;
    private MultipartFile image7Byte;
    private MultipartFile image8Byte;
    private MultipartFile image9Byte;
    private MultipartFile image10Byte;
    private int activestatus;
}