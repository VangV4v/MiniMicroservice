package com.vang.authsellerservice.controller;

import com.vang.authsellerservice.model.AuthRequestModel;
import com.vang.authsellerservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/seller/")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestModel model) {
        return authenticateService.authenticate(model);
    }
}