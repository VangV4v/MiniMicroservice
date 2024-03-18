package com.vang.customerservice.query.controller;

import com.vang.customerservice.query.model.CustomerResponseModel;
import com.vang.customerservice.query.service.CustomerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerQueryController {

    @Autowired
    private CustomerQueryService customerQueryService;

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponseModel> getDetail(@PathVariable("id") String id) {

        return customerQueryService.getDetail(id);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseModel>> getAll() {

        return customerQueryService.getAll();
    }
}