package com.vang.cartservice.command.grpc;

import com.vang.cartservice.command.grpc.gen.GetCustomerIDGrpc;
import com.vang.cartservice.command.grpc.gen.GetCustomerIDReply;
import com.vang.cartservice.command.grpc.gen.GetCustomerIDRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerIdByUsername {

    private final GetCustomerIDGrpc.GetCustomerIDBlockingStub getCustomerIDBlockingStub;

    @Autowired
    public GetCustomerIdByUsername(GetCustomerIDGrpc.GetCustomerIDBlockingStub getCustomerIDBlockingStub) {
        this.getCustomerIDBlockingStub = getCustomerIDBlockingStub;
    }

    public String getCustomerId(String username) {

        String customerid = null;
        GetCustomerIDReply reply = getCustomerIDBlockingStub.getInfo(GetCustomerIDRequest.newBuilder().setUsername(username).build());
        if(reply.getStatus()) {
            customerid = reply.getResponse();
        }
        return customerid;
    }
}