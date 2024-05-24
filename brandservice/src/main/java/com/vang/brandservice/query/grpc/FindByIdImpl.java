package com.vang.brandservice.query.grpc;

import com.google.gson.Gson;
import com.vang.brandservice.data.Brands;
import com.vang.brandservice.data.BrandsRepository;
import com.vang.brandservice.query.grpc.gen.FindBrandByIdRequest;
import com.vang.brandservice.query.grpc.gen.FindBrandByIdReply;
import com.vang.brandservice.query.grpc.gen.GetBrandByIdGrpc;
import com.vang.brandservice.query.grpcmodel.BrandJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class FindByIdImpl extends GetBrandByIdGrpc.GetBrandByIdImplBase {

    private final BrandsRepository brandsRepository;
    private final Gson gson = new Gson();

    @Autowired
    public FindByIdImpl(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    @Override
    public void getData(FindBrandByIdRequest request, StreamObserver<FindBrandByIdReply> responseObserver) {

        Brands brands = brandsRepository.findById(request.getBrandid()).get();
        BrandJsonModel model = new BrandJsonModel();
        BeanUtils.copyProperties(brands, model);
        String afterConvertJson = gson.toJson(model);
        FindBrandByIdReply reply = FindBrandByIdReply.newBuilder().setBrandjson(afterConvertJson).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}