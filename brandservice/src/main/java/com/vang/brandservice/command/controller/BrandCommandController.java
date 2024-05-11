package com.vang.brandservice.command.controller;

import com.vang.brandservice.command.model.BrandRequestModel;
import com.vang.brandservice.command.service.BrandCommandService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/brands/")
public class BrandCommandController {

    private final BrandCommandService brandCommandService;

    @Autowired
    public BrandCommandController(BrandCommandService brandCommandService) {
        this.brandCommandService = brandCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addBrand(@ModelAttribute BrandRequestModel model) {
        return brandCommandService.addBrand(model);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseCRUDCommon> updateBrand(@ModelAttribute BrandRequestModel model) {

        return brandCommandService.updateBrand(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteBrand(@PathVariable("id") String id) {

        return brandCommandService.deleteBrand(id);
    }
}