package com.vang.cartservice.command.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class CartRequestModel implements Serializable {
    private String cartid;
    private String customerid;
    private String productid;
    private String productdetail;
    private int quantity;
}