package com.vang.brandservice.query.controller;

import com.vang.brandservice.query.model.BrandResponseModel;
import com.vang.brandservice.query.service.BrandQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands/")
public class BrandQueryController {

    private final BrandQueryService brandQueryService;

    @Autowired
    public BrandQueryController(BrandQueryService brandQueryService) {
        this.brandQueryService = brandQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<BrandResponseModel> getDetail(@PathVariable("id") String id) {

        return brandQueryService.getDetail(id);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseModel>> getAll() {

        return brandQueryService.getAll();
    }
}