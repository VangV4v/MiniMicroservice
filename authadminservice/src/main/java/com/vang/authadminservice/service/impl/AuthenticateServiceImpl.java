package com.vang.authadminservice.service.impl;

import com.vang.authadminservice.auth.JwtService;
import com.vang.authadminservice.model.AuthRequestModel;
import com.vang.authadminservice.service.AuthenticateService;
//import org.apache.commons.lang.StringUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.message.MessageCode;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.service.ServiceCommon;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Autowired
    public AuthenticateServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<String> authenticate(AuthRequestModel model) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            if(authentication.isAuthenticated()) {
                return new ResponseEntity<>(jwtService.generateToken(model.getUsername()), HttpStatus.OK);
            }
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>(MessageCommon.getMessage(MessageCode.AUTHCUSTOMER001), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ServiceCommon.FAIL, HttpStatus.BAD_REQUEST);
    }
}
