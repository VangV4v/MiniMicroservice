package com.vang.authsellerservice.service;

import com.vang.authsellerservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<String> authenticate(AuthRequestModel model);
}
