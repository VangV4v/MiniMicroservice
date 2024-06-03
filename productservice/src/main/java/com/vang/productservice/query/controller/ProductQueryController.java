package com.vang.productservice.query.controller;

import com.vang.productservice.query.model.ProductResponseModel;
import com.vang.productservice.query.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @Autowired
    public ProductQueryController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProductResponseModel> getProductById(@PathVariable("id") String productId) {
        return productQueryService.getProductById(productId);
    }

    @GetMapping("seller/{sellerId}")
    public ResponseEntity<List<ProductResponseModel>> getProductBySeller(@PathVariable("sellerId") String sellerId) {
        return productQueryService.getProductBySeller(sellerId);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseModel>> getAllProducts() {
        return productQueryService.getAllProducts();
    }
}
