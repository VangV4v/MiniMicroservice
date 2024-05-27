package com.vang.productservice.command.service;

import com.vang.productservice.command.model.ProductRequestModel;
import com.vang.productservice.command.model.UpdateProductRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface ProductCommandService {

    ResponseEntity<ResponseCRUDCommon> addProduct(ProductRequestModel model);

    ResponseEntity<ResponseCRUDCommon> updateProduct(UpdateProductRequestModel model);

    ResponseEntity<ResponseCRUDCommon> deleteProduct(String productId);
}