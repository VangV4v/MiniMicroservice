package com.vang.customerservice.query.service;

import com.vang.customerservice.query.model.CustomerResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerQueryService {

    ResponseEntity<CustomerResponseModel> getDetail(String id);

    ResponseEntity<List<CustomerResponseModel>> getAll();

    ResponseEntity<CustomerResponseModel> getByKeyLogin(String key);
}
