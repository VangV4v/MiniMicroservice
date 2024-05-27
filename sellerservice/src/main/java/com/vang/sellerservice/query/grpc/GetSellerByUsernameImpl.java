package com.vang.sellerservice.query.grpc;

import com.google.gson.Gson;
import com.vang.sellerservice.data.Sellers;
import com.vang.sellerservice.data.SellersRepository;
import com.vang.sellerservice.query.grpcmodel.SellerJsonModel;
import com.vang.sellerservice.query.service.grpc.GetSellerByUsernameGrpc;
import com.vang.sellerservice.query.service.grpc.GetSellerByUsernameReply;
import com.vang.sellerservice.query.service.grpc.GetSellerByUsernameRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetSellerByUsernameImpl extends GetSellerByUsernameGrpc.GetSellerByUsernameImplBase {

    private final SellersRepository sellersRepository;

    @Autowired
    public GetSellerByUsernameImpl(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @Override
    public void getData(GetSellerByUsernameRequest request, StreamObserver<GetSellerByUsernameReply> responseObserver) {

        Gson gson = new Gson();
        Sellers sellers = sellersRepository.findByUsername(request.getUsername());
        SellerJsonModel jsonModel = new SellerJsonModel();
        BeanUtils.copyProperties(sellers, jsonModel);
        String jsonData = gson.toJson(jsonModel);
        GetSellerByUsernameReply reply = GetSellerByUsernameReply.newBuilder().setJsonResponse(jsonData).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}