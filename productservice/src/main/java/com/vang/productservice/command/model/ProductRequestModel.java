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
    private String productname;
    private String description;
    private BigDecimal price;
    private int quantity;
    private int soldquantity;
    private String createddate;
    private String lastmodified;
    private String defaultimage;
    private MultipartFile defaultImageByte;
    private String image1;
    private MultipartFile image1Byte;
    private String image2;
    private MultipartFile image2Byte;
    private String image3;
    private MultipartFile image3Byte;
    private String image4;
    private MultipartFile image4Byte;
    private String image5;
    private MultipartFile image5Byte;
    private String image6;
    private MultipartFile image6Byte;
    private String image7;
    private MultipartFile image7Byte;
    private String image8;
    private MultipartFile image8Byte;
    private String image9;
    private MultipartFile image9Byte;
    private String image10;
    private MultipartFile image10Byte;
    private int activestatus;
}