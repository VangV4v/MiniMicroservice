package com.vang.productservice.query.service;

import com.vang.productservice.query.model.ProductResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductQueryService {

    ResponseEntity<ProductResponseModel> getProductById(String productId);
    ResponseEntity<List<ProductResponseModel>> getProductBySeller(String sellerId);

    ResponseEntity<List<ProductResponseModel>> getAllProducts();
}