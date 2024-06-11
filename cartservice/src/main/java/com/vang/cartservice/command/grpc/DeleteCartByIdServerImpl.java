package com.vang.cartservice.command.grpc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vang.cartservice.command.grpc.gen.DeleteCartByIdGrpc;
import com.vang.cartservice.command.grpc.gen.GetCartByIdReply;
import com.vang.cartservice.command.grpc.gen.GetCartByIdRequest;
import com.vang.cartservice.data.CartsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class DeleteCartByIdServerImpl extends DeleteCartByIdGrpc.DeleteCartByIdImplBase {

    private final CartsRepository cartsRepository;

    @Autowired
    public DeleteCartByIdServerImpl(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @Override
    public void delete(GetCartByIdRequest request, StreamObserver<GetCartByIdReply> responseObserver) {

        String listCartIdRequest = request.getRequest();
        Gson gson = new Gson();
//        List<String> listString = gson.fromJson(listCartIdRequest, new TypeToken<ArrayList<String>>(){}.getClass());
        super.delete(request, responseObserver);
    }
}