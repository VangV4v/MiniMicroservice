package com.vang.categoryservice.command.controller;

import com.vang.categoryservice.command.model.CategoryRequestModel;
import com.vang.categoryservice.command.service.CategoryCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryCommandController {

    private final CategoryCommandService categoryCommandService;

    @Autowired
    public CategoryCommandController(CategoryCommandService categoryCommandService) {
        this.categoryCommandService = categoryCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addCategory(@RequestBody CategoryRequestModel model) {

        return categoryCommandService.addCategory(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateCategory(@RequestBody CategoryRequestModel model) {

        return categoryCommandService.updateCategory(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteCategory(@PathVariable("id") String id) {

        return categoryCommandService.deleteCategory(id);
    }
}
