package com.vang.confirmorderservice.grpc;

import com.vang.confirmorderservice.grpc.gen.GetAuthSellerInfoGrpc;
import com.vang.confirmorderservice.grpc.gen.GetAuthSellerInfoReply;
import com.vang.confirmorderservice.grpc.gen.GetAuthSellerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthSellerInformationClientImpl {

    private final GetAuthSellerInfoGrpc.GetAuthSellerInfoBlockingStub getAuthSellerInfoBlockingStub;

    @Autowired
    public GetAuthSellerInformationClientImpl(GetAuthSellerInfoGrpc.GetAuthSellerInfoBlockingStub getAuthSellerInfoBlockingStub) {
        this.getAuthSellerInfoBlockingStub = getAuthSellerInfoBlockingStub;
    }

    public String getAuthSellerInformation() {

        GetAuthSellerInfoReply reply = getAuthSellerInfoBlockingStub.getInfo(GetAuthSellerInfoRequest.newBuilder().setType(1).build());
        return reply.getUsername();
    }
}