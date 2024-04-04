package com.vang.authadminservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.message.MessageCode;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final AdminClientGrpc adminClientGrpc;

    @Autowired
    public MyUserDetailService(AdminClientGrpc adminClientGrpc) {
        this.adminClientGrpc = adminClientGrpc;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> response = adminClientGrpc.responseLoginAdmin(username);
        if(!Boolean.parseBoolean(response.get(FieldNameCommon.STATUS).toString())) {
            throw new UsernameNotFoundException(MessageCommon.getMessage(MessageCode.AUTHCUSTOMER002));
        }
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(response.get(FieldNameCommon.ROLE).toString()));
        return new User(username, response.get(FieldNameCommon.PASSWORD).toString(), listGrant);
    }

}