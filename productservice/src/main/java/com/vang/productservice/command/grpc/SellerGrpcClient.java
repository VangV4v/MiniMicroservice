package com.vang.productservice.command.grpc;

import com.vang.productservice.command.grpc.brand.gen.GetSellerByUsernameGrpc;
import com.vang.productservice.command.grpc.brand.gen.GetSellerByUsernameReply;
import com.vang.productservice.command.grpc.brand.gen.GetSellerByUsernameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerGrpcClient {

    private final GetSellerByUsernameGrpc.GetSellerByUsernameBlockingStub getSellerByUsernameBlockingStub;

    @Autowired
    public SellerGrpcClient(GetSellerByUsernameGrpc.GetSellerByUsernameBlockingStub getSellerByUsernameBlockingStub) {
        this.getSellerByUsernameBlockingStub = getSellerByUsernameBlockingStub;
    }

    public String getSellerByUsername(String username) {

        GetSellerByUsernameReply reply = getSellerByUsernameBlockingStub.getData(GetSellerByUsernameRequest.newBuilder().setUsername(username).build());
        return reply.getJsonResponse();
    }
}