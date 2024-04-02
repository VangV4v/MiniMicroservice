package com.vang.adminservice.query.service;

import com.vang.adminservice.query.model.AdminResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminQueryService {

    ResponseEntity<AdminResponseModel> getDetail(String id);
    ResponseEntity<List<AdminResponseModel>> getAll();
}