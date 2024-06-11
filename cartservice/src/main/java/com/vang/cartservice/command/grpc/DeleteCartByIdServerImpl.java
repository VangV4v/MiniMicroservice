package com.vang.cartservice.command.grpc;

import com.google.gson.Gson;
import com.vang.cartservice.command.grpc.gen.*;
import com.vang.cartservice.data.CartsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class DeleteCartByIdServerImpl extends DeleteCartByIdGrpc.DeleteCartByIdImplBase {

    private final CartsRepository cartsRepository;

    @Autowired
    public DeleteCartByIdServerImpl(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @Override
    public void delete(DeleteCartByIdRequest request, StreamObserver<DeleteCartByIdReply> responseObserver) {

        String cartId = request.getCartId();
        cartsRepository.deleteById(cartId);
        DeleteCartByIdReply reply = DeleteCartByIdReply.newBuilder().setStatus(true).build();
        responseObserver.onNext(reply);
    }

}