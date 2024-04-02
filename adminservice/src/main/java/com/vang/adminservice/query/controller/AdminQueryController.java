package com.vang.adminservice.query.controller;

import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.service.AdminQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminQueryController {

    private final AdminQueryService queryService;

    @Autowired
    public AdminQueryController(AdminQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminResponseModel> getDetail(@PathVariable("id") String id) {

        return queryService.getDetail(id);
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseModel>> getAll() {

        return queryService.getAll();
    }
}