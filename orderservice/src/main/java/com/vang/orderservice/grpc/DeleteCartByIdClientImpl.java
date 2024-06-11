package com.vang.orderservice.grpc;

import com.vang.orderservice.gen.DeleteCartByIdGrpc;
import com.vang.orderservice.gen.DeleteCartByIdReply;
import com.vang.orderservice.gen.DeleteCartByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCartByIdClientImpl {

    private final DeleteCartByIdGrpc.DeleteCartByIdBlockingStub deleteCartByIdBlockingStub;

    @Autowired
    public DeleteCartByIdClientImpl(DeleteCartByIdGrpc.DeleteCartByIdBlockingStub deleteCartByIdBlockingStub) {
        this.deleteCartByIdBlockingStub = deleteCartByIdBlockingStub;
    }

    public void deleteByCartId(String cartId) {
        DeleteCartByIdReply reply = deleteCartByIdBlockingStub.delete(DeleteCartByIdRequest.newBuilder().setCartId(cartId).build());
    }
}