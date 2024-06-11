package com.vang.cartservice.query.service;

import com.vang.cartservice.query.model.CartResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartQueryService {

    ResponseEntity<Object> getAllByCustomer();
}
