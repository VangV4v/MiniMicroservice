package com.vang.authcustomerservice.auth;

import com.vang.authcustomerservice.grpc.CustomerGrpcClient;
import org.apache.commons.lang.ObjectUtils;
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
public class MyUserdetailService implements UserDetailsService {

    @Autowired
    private CustomerGrpcClient customerGrpcClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, Object> replyCustomer = customerGrpcClient.getReplyLogin(username);
        if(!Boolean.parseBoolean(replyCustomer.get(FieldNameCommon.STATUS).toString())) {

            throw new UsernameNotFoundException(MessageCommon.getMessage(MessageCode.AUTHCUSTOMER002));
        }
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(replyCustomer.get(FieldNameCommon.ROLE).toString()));
        return new User(username, replyCustomer.get(FieldNameCommon.PASSWORD).toString(), listGrant);
    }
}