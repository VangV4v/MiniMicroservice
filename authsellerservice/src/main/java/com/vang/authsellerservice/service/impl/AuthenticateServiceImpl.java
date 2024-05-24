package com.vang.authsellerservice.service.impl;

import com.vang.authsellerservice.configuation.StoreData;
import com.vang.authsellerservice.jwt.JwtService;
import com.vang.authsellerservice.model.AuthRequestModel;
import com.vang.authsellerservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.service.FieldNameCommon;
import org.vang.minimicroservice.service.ServiceCommon;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, RedisTemplate<String, String> redisTemplate) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseEntity<String> authenticate(AuthRequestModel model) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            if(authentication.isAuthenticated()) {
                redisTemplate.opsForValue().set(FieldNameCommon.USERNAME, model.getUsername());
                return new ResponseEntity<>(jwtService.generateToken(model.getUsername()), HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(MessageCommon.AUTH_001, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ServiceCommon.FAIL, HttpStatus.BAD_REQUEST);
    }

}