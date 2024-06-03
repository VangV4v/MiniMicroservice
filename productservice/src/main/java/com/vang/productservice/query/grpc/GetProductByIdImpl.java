package com.vang.productservice.query.grpc;

import com.google.gson.Gson;
import com.vang.productservice.command.grpc.product.gen.GetProductByIdGrpc;
import com.vang.productservice.command.grpc.product.gen.GetProductByIdReply;
import com.vang.productservice.command.grpc.product.gen.GetProductByIdRequest;
import com.vang.productservice.data.Products;
import com.vang.productservice.data.ProductsRepository;
import com.vang.productservice.query.grpcmodel.ProductJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetProductByIdImpl extends GetProductByIdGrpc.GetProductByIdImplBase {

    private final ProductsRepository productsRepository;
    private final Gson gson = new Gson();

    @Autowired
    public GetProductByIdImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void getData(GetProductByIdRequest request, StreamObserver<GetProductByIdReply> responseObserver) {
        Products products = productsRepository.findById(request.getProductId()).get();
        ProductJsonModel jsonModel = new ProductJsonModel();
        BeanUtils.copyProperties(products, jsonModel);
        String response = gson.toJson(jsonModel);
        GetProductByIdReply reply = GetProductByIdReply.newBuilder().setJsonResponse(response).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}