package com.vang.addressservice.command.grpc;

import com.vang.addressservice.command.grpc.gen.GetCustomerIDGrpc;
import com.vang.addressservice.command.grpc.gen.GetCustomerIDReply;
import com.vang.addressservice.command.grpc.gen.GetCustomerIDRequest;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerIdByUsername {

    private final GetCustomerIDGrpc.GetCustomerIDBlockingStub getCustomerIDBlockingStub;

    public GetCustomerIdByUsername(GetCustomerIDGrpc.GetCustomerIDBlockingStub getCustomerIDBlockingStub) {
        this.getCustomerIDBlockingStub = getCustomerIDBlockingStub;
    }

    public String getCustomerIdByUsername(String username) {
        GetCustomerIDReply reply = getCustomerIDBlockingStub.getInfo(GetCustomerIDRequest.newBuilder().setUsername(username).build());
        return reply.getResponse();
    }

}