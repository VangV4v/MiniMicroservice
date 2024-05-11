package com.vang.customerservice.command.controller;

import com.vang.customerservice.command.model.CustomerRequestModel;
import com.vang.customerservice.command.model.CustomerUpdateRequestModel;
import com.vang.customerservice.command.service.CustomerCommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerCommandController {

    private final CustomerCommandService customerCommandService;

    @Autowired
    public CustomerCommandController(CustomerCommandService customerCommandService) {
        this.customerCommandService = customerCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addCustomer(@RequestBody CustomerRequestModel model) {

        return customerCommandService.addCustomer(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateCustomer(@Valid @ModelAttribute CustomerUpdateRequestModel model) {

        return customerCommandService.updateCustomer(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteCustomer(@PathVariable("id") String id) {

        return customerCommandService.deleteCustomer(id);
    }

}