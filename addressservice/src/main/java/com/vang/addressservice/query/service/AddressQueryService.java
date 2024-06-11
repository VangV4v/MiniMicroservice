package com.vang.addressservice.query.service;

import org.springframework.http.ResponseEntity;

public interface AddressQueryService {

    ResponseEntity<Object> getAllByCustomerID();
}
