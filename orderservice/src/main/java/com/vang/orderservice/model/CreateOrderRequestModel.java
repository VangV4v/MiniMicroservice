package com.vang.orderservice.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestModel {
    private String cartId = null;
    private String addressId;
    private String note;
    private List<String> listCartId = null;
}