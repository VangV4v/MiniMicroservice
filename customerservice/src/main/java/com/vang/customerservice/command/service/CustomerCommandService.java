package com.vang.customerservice.command.service;

import com.vang.customerservice.command.model.CustomerRequestModel;
import org.springframework.http.ResponseEntity;

public interface CustomerCommandService {

    ResponseEntity<String> addCustomer(CustomerRequestModel model);
    ResponseEntity<String> updateCustomer(CustomerRequestModel model);
    ResponseEntity<String> deleteCustomer(String id);
}