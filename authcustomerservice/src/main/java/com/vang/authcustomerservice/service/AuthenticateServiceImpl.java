package com.vang.authcustomerservice.service;

import com.vang.authcustomerservice.auth.JwtService;
import com.vang.authcustomerservice.model.AuthRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> authenticate(AuthRequestModel model) {

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            if(authentication.isAuthenticated()) {
                return new ResponseEntity<>(jwtService.generateToken(model.getUsername()), HttpStatus.OK);
            }
        }catch (BadCredentialsException loginfail) {
            return new ResponseEntity<>("Username is not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }

}