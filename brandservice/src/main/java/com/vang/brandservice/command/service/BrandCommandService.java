package com.vang.brandservice.command.service;

import com.vang.brandservice.command.model.BrandRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface BrandCommandService {

    ResponseEntity<ResponseCRUDCommon> addBrand(BrandRequestModel model);
    ResponseEntity<ResponseCRUDCommon> updateBrand(BrandRequestModel model);
    ResponseEntity<ResponseCRUDCommon> deleteBrand(String id);

}