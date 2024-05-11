package com.vang.categoryservice.command.service;

import com.vang.categoryservice.command.model.CategoryRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface CategoryCommandService {

    ResponseEntity<ResponseCRUDCommon> addCategory(CategoryRequestModel model);

    ResponseEntity<ResponseCRUDCommon> updateCategory(CategoryRequestModel model);

    ResponseEntity<ResponseCRUDCommon> deleteCategory(String categoryId);
}