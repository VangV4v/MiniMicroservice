package com.vang.authcustomerservice.controller;

import com.vang.authcustomerservice.model.AuthRequestModel;
import com.vang.authcustomerservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/customer/")
@CrossOrigin
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