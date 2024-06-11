package com.vang.orderservice.model;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestModel implements Serializable {

    private long orderid;
    private String orderdate;
    private String productid;
    private String productdetail;
    private String customerid;
    private String customerdetail;
    private String addressid;
    private String addressdetail;
    private String sellerid;
    private String note;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalprice;
    private int confirmstatus;
}