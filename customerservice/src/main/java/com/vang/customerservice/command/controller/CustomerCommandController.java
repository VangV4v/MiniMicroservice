package com.vang.customerservice.command.controller;

import com.vang.customerservice.command.model.CustomerRequestModel;
import com.vang.customerservice.command.service.CustomerCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerCommandController {

    @Autowired
    private CustomerCommandService customerCommandService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequestModel model) {

        return customerCommandService.addCustomer(model);
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerRequestModel model) {

        return customerCommandService.updateCustomer(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {

        return customerCommandService.deleteCustomer(id);
    }

}