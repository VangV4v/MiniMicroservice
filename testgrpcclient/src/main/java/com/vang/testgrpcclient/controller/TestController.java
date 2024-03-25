package com.vang.testgrpcclient.controller;
import com.vang.testgrpcclient.service.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grpc-client/test/")
public class TestController {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @GetMapping("{username}")
    public String test(@PathVariable("username") String username) {

        return customerServiceClient.loginRequest(username);
    }
}
