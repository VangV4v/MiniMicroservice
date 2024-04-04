package com.vang.authadminservice.service;

import com.vang.authadminservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    public ResponseEntity<String> authenticate(AuthRequestModel model);
}
