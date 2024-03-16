package com.vang.brandservice.query.service;

import com.vang.brandservice.query.model.BrandResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandQueryService {

    ResponseEntity<BrandResponseModel> getDetail(String id);
    ResponseEntity<List<BrandResponseModel>> getAll();

}