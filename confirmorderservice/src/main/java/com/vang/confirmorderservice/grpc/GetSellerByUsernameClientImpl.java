package com.vang.confirmorderservice.grpc;

import com.vang.confirmorderservice.grpc.gen.GetSellerByUsernameGrpc;
import com.vang.confirmorderservice.grpc.gen.GetSellerByUsernameReply;
import com.vang.confirmorderservice.grpc.gen.GetSellerByUsernameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSellerByUsernameClientImpl {

    private final GetSellerByUsernameGrpc.GetSellerByUsernameBlockingStub getSellerByUsernameBlockingStub;

    @Autowired
    public GetSellerByUsernameClientImpl(GetSellerByUsernameGrpc.GetSellerByUsernameBlockingStub getSellerByUsernameBlockingStub) {
        this.getSellerByUsernameBlockingStub = getSellerByUsernameBlockingStub;
    }

    public String getSellerByUsername(String username) {

        GetSellerByUsernameReply reply = getSellerByUsernameBlockingStub.getData(GetSellerByUsernameRequest.newBuilder().setUsername(username).build());
        return reply.getJsonResponse();
    }
}
