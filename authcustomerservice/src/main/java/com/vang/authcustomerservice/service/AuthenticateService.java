package com.vang.authcustomerservice.service;

import com.vang.authcustomerservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<String> authenticate(AuthRequestModel model);
}
