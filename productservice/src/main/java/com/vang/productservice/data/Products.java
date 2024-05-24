package com.vang.productservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Products {

    @Id
    @Column(name = "productid")
    private String productid;
    @Column(name = "brandid")
    private String brandid;
    @Column(name = "branddetail")
    private String branddetail;
    @Column(name = "categoryid")
    private String categoryid;
    @Column(name = "categorydetail")
    private String categorydetail;
    @Column(name = "productname")
    private String productname;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "soldquantity")
    private int soldquantity;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "lastmodified")
    private String lastmodified;
    @Column(name = "defaultimage")
    private String defaultimage;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "image3")
    private String image3;
    @Column(name = "image4")
    private String image4;
    @Column(name = "image5")
    private String image5;
    @Column(name = "image6")
    private String image6;
    @Column(name = "image7")
    private String image7;
    @Column(name = "image8")
    private String image8;
    @Column(name = "image9")
    private String image9;
    @Column(name = "image10")
    private String image10;
    @Column(name = "activestatus")
    private int activestatus;
}