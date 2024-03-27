package com.vang.authcustomerservice.service;

import com.vang.authcustomerservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface AuthenticateService {

    ResponseEntity<String> authenticate(AuthRequestModel model);
}
