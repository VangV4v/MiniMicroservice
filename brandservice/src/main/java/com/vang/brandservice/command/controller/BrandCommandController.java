package com.vang.brandservice.command.controller;

import com.vang.brandservice.command.model.BrandRequestModel;
import com.vang.brandservice.command.service.BrandCommandService;
import com.vang.brandservice.data.BrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands/")
public class BrandCommandController {

    @Autowired
    private BrandCommandService brandCommandService;

    @Autowired
    private BrandsRepository repository;

    @PostMapping
    public ResponseEntity<String> addBrand(@RequestBody BrandRequestModel model) {

        return brandCommandService.addBrand(model);
    }

    @PutMapping
    public ResponseEntity<String> updateBrand(@RequestBody BrandRequestModel model) {

        return brandCommandService.updateBrand(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") String id) {

        return brandCommandService.deleteBrand(id);
    }
}