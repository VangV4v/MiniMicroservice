package com.vang.categoryservice.query.controller;

import com.vang.categoryservice.query.model.CategoryResponseModel;
import com.vang.categoryservice.query.service.CategoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @Autowired
    public CategoryQueryController(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseModel> getById(@PathVariable("id") String id) {
        return categoryQueryService.getByCategoryId(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseModel>> getAll() {

        return categoryQueryService.getAllCategories();
    }
}
