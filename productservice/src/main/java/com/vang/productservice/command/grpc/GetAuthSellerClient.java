package com.vang.productservice.command.grpc;

import com.vang.productservice.grpc.gen.GetAuthSellerInfoGrpc;
import com.vang.productservice.grpc.gen.GetAuthSellerInfoReply;
import com.vang.productservice.grpc.gen.GetAuthSellerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthSellerClient {

    private final GetAuthSellerInfoGrpc.GetAuthSellerInfoBlockingStub authSellerInfoBlockingStub;

    @Autowired
    public GetAuthSellerClient(GetAuthSellerInfoGrpc.GetAuthSellerInfoBlockingStub authSellerInfoBlockingStub) {
        this.authSellerInfoBlockingStub = authSellerInfoBlockingStub;
    }

    public String getAuthSeller() {

        GetAuthSellerInfoReply reply = authSellerInfoBlockingStub.getInfo(GetAuthSellerInfoRequest.newBuilder().setType(1).build());
        return reply.getStatus() ? reply.getUsername() : null;
    }

}