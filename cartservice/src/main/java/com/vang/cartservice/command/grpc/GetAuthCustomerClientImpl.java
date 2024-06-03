package com.vang.cartservice.command.grpc;

import com.vang.cartservice.command.grpc.gen.GetAuthCustomerInfoGrpc;
import com.vang.cartservice.command.grpc.gen.GetAuthCustomerInfoReply;
import com.vang.cartservice.command.grpc.gen.GetAuthCustomerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthCustomerClientImpl {

    private GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub;

    @Autowired
    public GetAuthCustomerClientImpl(GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub) {
        this.getAuthCustomerInfoBlockingStub = getAuthCustomerInfoBlockingStub;
    }

    public String getAuthInfomation() {

        String username = null;
        GetAuthCustomerInfoReply reply = getAuthCustomerInfoBlockingStub.getInfo(GetAuthCustomerInfoRequest.newBuilder().build());
        if(reply.getStatus()) {
            username = reply.getUsername();
        }
        return username;
    }

}