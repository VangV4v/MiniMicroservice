package com.vang.brandservice.command.service;

import com.vang.brandservice.command.model.BrandRequestModel;
import org.springframework.http.ResponseEntity;

public interface BrandCommandService {

    ResponseEntity<String> addBrand(BrandRequestModel model);
    ResponseEntity<String> updateBrand(BrandRequestModel model);
    ResponseEntity<String> deleteBrand(String id);

}