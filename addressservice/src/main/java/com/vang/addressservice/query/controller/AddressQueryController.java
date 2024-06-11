package com.vang.addressservice.query.controller;

import com.vang.addressservice.query.service.AddressQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/addresses/")
public class AddressQueryController {

    private final AddressQueryService addressQueryService;

    @Autowired
    public AddressQueryController(AddressQueryService addressQueryService) {
        this.addressQueryService = addressQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllByCustomer() {
        return addressQueryService.getAllByCustomerID();
    }
}