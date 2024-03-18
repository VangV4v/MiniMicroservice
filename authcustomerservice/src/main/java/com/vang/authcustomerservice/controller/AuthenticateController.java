package com.vang.authcustomerservice.controller;

import com.vang.authcustomerservice.model.AuthRequestModel;
import com.vang.authcustomerservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/authenticate/")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestModel model) {

        return authenticateService.authenticate(model);
    }
}