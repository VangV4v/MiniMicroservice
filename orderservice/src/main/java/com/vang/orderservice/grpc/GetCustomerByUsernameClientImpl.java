package com.vang.orderservice.grpc;

import com.vang.orderservice.gen.GetCustomerByUsernameGrpc;
import com.vang.orderservice.gen.GetCustomerByUsernameReply;
import com.vang.orderservice.gen.GetCustomerByUsernameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerByUsernameClientImpl {

    private final GetCustomerByUsernameGrpc.GetCustomerByUsernameBlockingStub getCustomerByUsernameBlockingStub;

    @Autowired
    public GetCustomerByUsernameClientImpl(GetCustomerByUsernameGrpc.GetCustomerByUsernameBlockingStub getCustomerByUsernameBlockingStub) {
        this.getCustomerByUsernameBlockingStub = getCustomerByUsernameBlockingStub;
    }

    public String getCustomerByUsername(String username) {

        GetCustomerByUsernameReply reply = getCustomerByUsernameBlockingStub.getData(GetCustomerByUsernameRequest.newBuilder().setUsername(username).build());
        return reply.getResponse();
    }
}