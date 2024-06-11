package com.vang.orderservice.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartJsonModel implements Serializable {
    private String cartid;
    private String customerid;
    private String productid;
    private String productdetail;
    private int quantity;
}