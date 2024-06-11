package com.vang.orderservice.service;

import com.vang.orderservice.data.Orders;
import com.vang.orderservice.model.CreateOrderRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface OrderService {

    ResponseEntity<ResponseCRUDCommon> createOrder(CreateOrderRequestModel model);

    ResponseEntity<Object> getAllOrders();
}