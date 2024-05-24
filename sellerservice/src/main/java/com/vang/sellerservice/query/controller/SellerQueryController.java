package com.vang.sellerservice.query.controller;

import com.vang.sellerservice.query.model.SellerResponseModel;
import com.vang.sellerservice.query.service.SellerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers/")
public class SellerQueryController {

    private final SellerQueryService sellerQueryService;

    @Autowired
    public SellerQueryController(SellerQueryService sellerQueryService) {
        this.sellerQueryService = sellerQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseModel> getSellerById(@PathVariable("id") String sellerId) {
        return sellerQueryService.getBySellerId(sellerId);
    }

    @GetMapping
    public ResponseEntity<List<SellerResponseModel>> getAllSellers() {
        return sellerQueryService.getAllSellers();
    }
}