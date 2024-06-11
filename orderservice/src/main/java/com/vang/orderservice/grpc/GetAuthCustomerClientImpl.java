package com.vang.orderservice.grpc;

import com.vang.orderservice.gen.GetAuthCustomerInfoGrpc;
import com.vang.orderservice.gen.GetAuthCustomerInfoReply;
import com.vang.orderservice.gen.GetAuthCustomerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthCustomerClientImpl {

    private final GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub;

    @Autowired
    public GetAuthCustomerClientImpl(GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub) {
        this.getAuthCustomerInfoBlockingStub = getAuthCustomerInfoBlockingStub;
    }

    public String getAuthCustomerInformation() {

        GetAuthCustomerInfoReply reply = getAuthCustomerInfoBlockingStub.getInfo(GetAuthCustomerInfoRequest.newBuilder().setType(1).build());
        return reply.getUsername();
    }
}