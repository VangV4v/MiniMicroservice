package com.vang.cartservice.query.controller;

import com.vang.cartservice.query.model.CartResponseModel;
import com.vang.cartservice.query.service.CartQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts/")
public class CartQueryController {

    private final CartQueryService cartQueryService;

    @Autowired
    public CartQueryController(CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllByCustomer() {
        return cartQueryService.getAllByCustomer();
    }
}