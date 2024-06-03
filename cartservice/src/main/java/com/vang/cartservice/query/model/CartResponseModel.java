package com.vang.cartservice.query.model;

import com.vang.cartservice.command.grpcmodel.ProductJsonModel;
import lombok.Data;
import java.io.Serializable;

@Data
public class CartResponseModel implements Serializable {
    private String cartid;
    private String customerid;
    private String productid;
    private ProductJsonModel productdetail;
    private int quantity;
}