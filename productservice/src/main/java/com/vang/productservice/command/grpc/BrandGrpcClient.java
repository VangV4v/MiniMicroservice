package com.vang.productservice.command.grpc;

import com.vang.productservice.command.grpc.brand.gen.FindBrandByIdRequest;
import com.vang.productservice.command.grpc.brand.gen.GetBrandByIdGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandGrpcClient {

    private final GetBrandByIdGrpc.GetBrandByIdBlockingStub getBrandByIdBlockingStub;

    @Autowired
    public BrandGrpcClient(GetBrandByIdGrpc.GetBrandByIdBlockingStub getBrandByIdBlockingStub) {
        this.getBrandByIdBlockingStub = getBrandByIdBlockingStub;
    }

    public String getBrandJson(String brandId) {

        return getBrandByIdBlockingStub.getData(FindBrandByIdRequest.newBuilder().setBrandid(brandId).build()).getBrandjson();
    }
}