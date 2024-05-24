package com.vang.productservice.command.service;

import com.vang.productservice.command.model.ProductRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface ProductCommandService {

    ResponseEntity<ResponseCRUDCommon> addProduct(ProductRequestModel model);
}