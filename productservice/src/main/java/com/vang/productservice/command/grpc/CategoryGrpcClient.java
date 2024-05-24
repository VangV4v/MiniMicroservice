package com.vang.productservice.command.grpc;

import com.vang.productservice.command.grpc.gen.FindCategoryByIdRequest;
import com.vang.productservice.command.grpc.gen.GetCategoryByIdGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryGrpcClient {

    private final GetCategoryByIdGrpc.GetCategoryByIdBlockingStub getCategoryByIdBlockingStub;

    @Autowired
    public CategoryGrpcClient(GetCategoryByIdGrpc.GetCategoryByIdBlockingStub getCategoryByIdBlockingStub) {
        this.getCategoryByIdBlockingStub = getCategoryByIdBlockingStub;
    }

    public String getCategoryJson(String id) {
        return getCategoryByIdBlockingStub.getData(FindCategoryByIdRequest.newBuilder().setCategoryid(id).build()).getCategoryJson();
    }
}