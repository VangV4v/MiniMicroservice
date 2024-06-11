package com.vang.addressservice.command.grpc;

import com.vang.addressservice.grpc.gen.GetAuthCustomerInfoGrpc;
import com.vang.addressservice.grpc.gen.GetAuthCustomerInfoReply;
import com.vang.addressservice.grpc.gen.GetAuthCustomerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthCustomerClientImpl {

    private final GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub;

    @Autowired
    public GetAuthCustomerClientImpl(GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub) {
        this.getAuthCustomerInfoBlockingStub = getAuthCustomerInfoBlockingStub;
    }

    public String getAuthCustomer() {

        GetAuthCustomerInfoReply reply = getAuthCustomerInfoBlockingStub.getInfo(GetAuthCustomerInfoRequest.newBuilder().setType(0).build());
        return reply.getUsername();
    }
}
