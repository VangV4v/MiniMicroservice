package com.vang.orderservice.data;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private long orderid;
    @Column(name = "orderdate")
    private String orderdate;
    @Column(name = "productid")
    private String productid;
    @Column(name = "productdetail", length = 2000)
    private String productdetail;
    @Column(name = "customerid")
    private String customerid;
    @Column(name = "customerdetail", length = 2000)
    private String customerdetail;
    @Column(name = "addressid")
    private String addressid;
    @Column(name = "addressdetail", length = 1000)
    private String addressdetail;
    @Column(name = "sellerid")
    private String sellerid;
    @Column(name = "note", length = 500)
    private String note;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "totalprice")
    private BigDecimal totalprice;
    @Column(name = "confirmstatus")
    private int confirmstatus;
}