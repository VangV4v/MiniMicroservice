package com.vang.productservice.command.controller;

import com.vang.productservice.command.model.ProductRequestModel;
import com.vang.productservice.command.model.UpdateProductRequestModel;
import com.vang.productservice.command.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @Autowired
    public ProductCommandController(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addProduct(@ModelAttribute ProductRequestModel model) {

        return productCommandService.addProduct(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateProduct(@ModelAttribute UpdateProductRequestModel model) {
        return productCommandService.updateProduct(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteProduct(@PathVariable("id") String id) {
        return productCommandService.deleteProduct(id);
    }
}