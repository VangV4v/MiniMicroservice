package com.vang.categoryservice.query.service;

import com.vang.categoryservice.query.model.CategoryResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryQueryService {

    ResponseEntity<CategoryResponseModel> getByCategoryId(String category);

    ResponseEntity<List<CategoryResponseModel>> getAllCategories();
}