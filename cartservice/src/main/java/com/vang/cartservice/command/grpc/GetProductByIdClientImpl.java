package com.vang.cartservice.command.grpc;

import com.vang.cartservice.command.grpc.gen.GetProductByIdGrpc;
import com.vang.cartservice.command.grpc.gen.GetProductByIdReply;
import com.vang.cartservice.command.grpc.gen.GetProductByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdClientImpl {

    private final GetProductByIdGrpc.GetProductByIdBlockingStub getProductByIdBlockingStub;

    @Autowired
    public GetProductByIdClientImpl(GetProductByIdGrpc.GetProductByIdBlockingStub getProductByIdBlockingStub) {
        this.getProductByIdBlockingStub = getProductByIdBlockingStub;
    }

    public String getProductById(String productId) {

        GetProductByIdReply reply = getProductByIdBlockingStub.getData(GetProductByIdRequest.newBuilder().setProductId(productId).build());
        return reply.getJsonResponse();
    }
}