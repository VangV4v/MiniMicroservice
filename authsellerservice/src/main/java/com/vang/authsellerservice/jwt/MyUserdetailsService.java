package com.vang.authsellerservice.jwt;

import com.vang.authsellerservice.grpc.LoginClientGrpcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.List;
import java.util.Map;

@Service
public class MyUserdetailsService implements UserDetailsService {

    private final LoginClientGrpcImpl loginClientGrpc;

    @Autowired
    public MyUserdetailsService(LoginClientGrpcImpl loginClientGrpc) {
        this.loginClientGrpc = loginClientGrpc;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, Object> resultLogin = loginClientGrpc.checkLogin(username);
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(resultLogin.get(FieldNameCommon.PASSWORD).toString()));
        return new User(username, resultLogin.get(FieldNameCommon.PASSWORD).toString(), authorityList);
    }

}