package com.vang.customerservice.command.service;

import com.vang.customerservice.command.model.CustomerRequestModel;
import com.vang.customerservice.command.model.CustomerUpdateRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface CustomerCommandService {

    ResponseEntity<ResponseCRUDCommon> addCustomer(CustomerRequestModel model);
    ResponseEntity<ResponseCRUDCommon> updateCustomer(CustomerUpdateRequestModel model);
    ResponseEntity<ResponseCRUDCommon> deleteCustomer(String id);
}