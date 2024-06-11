package com.vang.orderservice.controller;

import com.vang.orderservice.model.CreateOrderRequestModel;
import com.vang.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/orders/")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> createOrder(@RequestBody CreateOrderRequestModel orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping
    public ResponseEntity<Object> getOrders() {
        return orderService.getAllOrders();
    }

}