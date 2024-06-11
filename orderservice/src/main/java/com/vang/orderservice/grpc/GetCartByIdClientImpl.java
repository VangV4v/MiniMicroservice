package com.vang.orderservice.grpc;

import com.google.gson.Gson;
import com.vang.orderservice.gen.GetCartByIdGrpc;
import com.vang.orderservice.gen.GetCartByIdReply;
import com.vang.orderservice.gen.GetCartByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCartByIdClientImpl {

    private final GetCartByIdGrpc.GetCartByIdBlockingStub getCartByIdBlockingStub;

    @Autowired
    public GetCartByIdClientImpl(GetCartByIdGrpc.GetCartByIdBlockingStub getCartByIdBlockingStub) {
        this.getCartByIdBlockingStub = getCartByIdBlockingStub;
    }

    public String getCartData(String cartId) {

        GetCartByIdReply reply = getCartByIdBlockingStub.getData(GetCartByIdRequest.newBuilder().setType(1).setRequest(cartId).build());
        if(reply.getStatus()) {
            return reply.getResponse();
        }
        return null;
    }

    public String getListCartData(List<String> cartIds) {

        Gson gson = new Gson();
        String convertToJson = gson.toJson(cartIds);
        GetCartByIdReply reply = getCartByIdBlockingStub.getData(GetCartByIdRequest.newBuilder().setType(2).setRequest(convertToJson).build());
        if(reply.getStatus()) {
            return reply.getResponse();
        }
        return null;
    }

}