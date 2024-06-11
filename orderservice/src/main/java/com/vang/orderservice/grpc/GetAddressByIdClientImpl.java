package com.vang.orderservice.grpc;

import com.vang.orderservice.gen.GetAddressByIdGrpc;
import com.vang.orderservice.gen.GetAddressByIdReply;
import com.vang.orderservice.gen.GetAddressByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAddressByIdClientImpl {

    private final GetAddressByIdGrpc.GetAddressByIdBlockingStub getAddressByIdBlockingStub;

    @Autowired
    public GetAddressByIdClientImpl(GetAddressByIdGrpc.GetAddressByIdBlockingStub getAddressByIdBlockingStub) {
        this.getAddressByIdBlockingStub = getAddressByIdBlockingStub;
    }

    public String getAddressByIdJson(String addressId) {

        GetAddressByIdReply reply = getAddressByIdBlockingStub.getData(GetAddressByIdRequest.newBuilder().setAddressId(addressId).build());
        return reply.getResponse();
    }
}