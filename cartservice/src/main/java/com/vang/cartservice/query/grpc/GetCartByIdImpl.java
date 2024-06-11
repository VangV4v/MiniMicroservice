package com.vang.cartservice.query.grpc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vang.cartservice.command.grpc.gen.GetCartByIdGrpc;
import com.vang.cartservice.command.grpc.gen.GetCartByIdReply;
import com.vang.cartservice.command.grpc.gen.GetCartByIdRequest;
import com.vang.cartservice.data.Carts;
import com.vang.cartservice.data.CartsRepository;
import com.vang.cartservice.query.grpcmodel.CartJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class GetCartByIdImpl extends GetCartByIdGrpc.GetCartByIdImplBase {

    private final CartsRepository cartsRepository;

    @Autowired
    public GetCartByIdImpl(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @Override
    public void getData(GetCartByIdRequest request, StreamObserver<GetCartByIdReply> responseObserver) {
        Gson gson = new Gson();
        GetCartByIdReply reply = null;
        if(request.getType() == 1) {
            Carts carts = cartsRepository.findById(request.getRequest()).get();
            CartJsonModel cartJsonModel = new CartJsonModel();
            BeanUtils.copyProperties(carts, cartJsonModel);
            String response = gson.toJson(cartJsonModel);
            reply = GetCartByIdReply.newBuilder().setResponse(response).setStatus(true).build();
        } else {
            List<String> listCartId = gson.fromJson(request.getRequest(), new TypeToken<List<String>>(){}.getType());
            List<Carts> listCarts = cartsRepository.findAllByCartId(listCartId);
            List<CartJsonModel> listModels = new ArrayList<>();
            listCarts.forEach(e -> {
                CartJsonModel model = new CartJsonModel();
                BeanUtils.copyProperties(e, model);
                listModels.add(model);
            });
            String response = gson.toJson(listModels);
            reply = GetCartByIdReply.newBuilder().setResponse(response).setStatus(true).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}