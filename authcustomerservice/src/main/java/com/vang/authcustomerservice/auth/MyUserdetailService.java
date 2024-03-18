package com.vang.authcustomerservice.auth;

import com.vang.authcustomerservice.data.Customers;
import com.vang.authcustomerservice.data.CustomersRepository;
import com.vang.authcustomerservice.model.CustomerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class MyUserdetailService implements UserDetailsService {

    @Autowired
    private CustomersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customers customers = repository.findByLoginType(username);
        if(ObjectUtils.isEmpty(customers)) {

            throw new UsernameNotFoundException("Username not found");
        }
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customers, model);
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(model.getRole()));
        return new User(model.getUsername(), model.getPassword(), listGrant);
    }
}