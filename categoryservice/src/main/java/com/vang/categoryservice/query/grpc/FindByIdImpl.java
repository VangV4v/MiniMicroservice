package com.vang.categoryservice.query.grpc;

import com.google.gson.Gson;
import com.vang.categoryservice.data.Categories;
import com.vang.categoryservice.data.CategoriesRepository;
import com.vang.categoryservice.query.grpc.gen.GetCategoryByIdGrpc;
import com.vang.categoryservice.query.grpc.gen.FindCategoryByIdRequest;
import com.vang.categoryservice.query.grpc.gen.FindCategoryByIdReply;
import com.vang.categoryservice.query.grpcmodel.CategoryJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class FindByIdImpl extends GetCategoryByIdGrpc.GetCategoryByIdImplBase {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public FindByIdImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void getData(FindCategoryByIdRequest request, StreamObserver<FindCategoryByIdReply> responseObserver) {

        Gson gson = new Gson();
        Categories categories = categoriesRepository.findById(request.getCategoryid()).get();
        CategoryJsonModel jsonModel = new CategoryJsonModel();
        BeanUtils.copyProperties(categories, jsonModel);
        String data = gson.toJson(jsonModel);
        System.out.println(data);
        responseObserver.onNext(FindCategoryByIdReply.newBuilder().setCategoryJson(data).build());
        responseObserver.onCompleted();

    }

}