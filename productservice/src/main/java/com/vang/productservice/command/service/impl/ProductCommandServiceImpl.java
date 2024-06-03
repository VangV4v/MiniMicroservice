package com.vang.productservice.command.service.impl;

import com.google.gson.Gson;
import com.vang.productservice.command.command.CreateProductCommand;
import com.vang.productservice.command.command.DeleteProductCommand;
import com.vang.productservice.command.command.UpdateProductCommand;
import com.vang.productservice.command.grpc.BrandGrpcClient;
import com.vang.productservice.command.grpc.CategoryGrpcClient;
import com.vang.productservice.command.grpc.GetAuthSellerClient;
import com.vang.productservice.command.grpc.SellerGrpcClient;
import com.vang.productservice.command.grpcmodel.BrandJsonModel;
import com.vang.productservice.command.grpcmodel.CategoryJsonModel;
import com.vang.productservice.command.grpcmodel.SellerJsonModel;
import com.vang.productservice.command.model.ProductRequestModel;
import com.vang.productservice.command.model.UpdateProductRequestModel;
import com.vang.productservice.command.service.ProductCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.DateCommon;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

import java.io.IOException;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final Gson gson = new Gson();
    private final CategoryGrpcClient categoryGrpcClient;
    private final BrandGrpcClient brandGrpcClient;
    private final CommandGateway commandGateway;
    private final GetAuthSellerClient getAuthSellerClient;
    private final SellerGrpcClient sellerGrpcClient;

    @Autowired
    public ProductCommandServiceImpl(CategoryGrpcClient categoryGrpcClient, BrandGrpcClient brandGrpcClient, CommandGateway commandGateway, SellerGrpcClient sellerGrpcClient, GetAuthSellerClient getAuthSellerClient) {
        this.categoryGrpcClient = categoryGrpcClient;
        this.brandGrpcClient = brandGrpcClient;
        this.commandGateway = commandGateway;
        this.sellerGrpcClient = sellerGrpcClient;
        this.getAuthSellerClient = getAuthSellerClient;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addProduct(ProductRequestModel model) {

        String categoryJson = categoryGrpcClient.getCategoryJson(model.getCategoryid());
        String brandJson = brandGrpcClient.getBrandJson(model.getBrandid());
        String username = getAuthSellerClient.getAuthSeller();
        String sellerJson = sellerGrpcClient.getSellerByUsername(username);
        SellerJsonModel sellerJsonModel = gson.fromJson(sellerJson, SellerJsonModel.class);
        CategoryJsonModel categoryJsonModel = gson.fromJson(categoryJson, CategoryJsonModel.class);
        BrandJsonModel brandJsonModel = gson.fromJson(brandJson, BrandJsonModel.class);
        CreateProductCommand command = new CreateProductCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setSellerid(sellerJsonModel.getSellerid());
        command.setSellerdetail(sellerJson);
        command.setBrandid(brandJsonModel.getBrandid());
        command.setBranddetail(brandJson);
        command.setCategoryid(categoryJsonModel.getCategoryid());
        command.setCategorydetail(categoryJson);
        command.setCreateddate(DateCommon.getDateTimeCurrent());
        command.setActivestatus(NumberUtils.ONE);
        try {
            if(!ObjectUtils.isEmpty(model.getDefaultImageByte())) {
                command.setDefaultImageByte(model.getDefaultImageByte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage1Byte())) {
                command.setImage1Byte(model.getImage1Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage2Byte())) {
                command.setImage2Byte(model.getImage2Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage3Byte())) {
                command.setImage3Byte(model.getImage3Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage4Byte())) {
                command.setImage4Byte(model.getImage4Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage5Byte())) {
                command.setImage5Byte(model.getImage5Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage6Byte())) {
                command.setImage6Byte(model.getImage6Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage7Byte())) {
                command.setImage7Byte(model.getImage7Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage8Byte())) {
                command.setImage8Byte(model.getImage8Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage9Byte())) {
                command.setImage9Byte(model.getImage9Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage10Byte())) {
                command.setImage10Byte(model.getImage10Byte().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        commandGateway.sendAndWait(command);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Product.CREATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateProduct(UpdateProductRequestModel model) {

        UpdateProductCommand command = new UpdateProductCommand();
        String categoryJson = categoryGrpcClient.getCategoryJson(model.getCategoryid());
        String brandJson = brandGrpcClient.getBrandJson(model.getBrandid());
        String username = getAuthSellerClient.getAuthSeller();
        String sellerJson = sellerGrpcClient.getSellerByUsername(username);
        SellerJsonModel sellerJsonModel = gson.fromJson(sellerJson, SellerJsonModel.class);
        CategoryJsonModel categoryJsonModel = gson.fromJson(categoryJson, CategoryJsonModel.class);
        BrandJsonModel brandJsonModel = gson.fromJson(brandJson, BrandJsonModel.class);
        BeanUtils.copyProperties(model, command);
        command.setSellerid(sellerJsonModel.getSellerid());
        command.setSellerdetail(sellerJson);
        command.setBrandid(brandJsonModel.getBrandid());
        command.setBranddetail(brandJson);
        command.setCategoryid(categoryJsonModel.getCategoryid());
        command.setCategorydetail(categoryJson);
        try {
            if(!ObjectUtils.isEmpty(model.getDefaultImageByte())) {
                command.setDefaultImageByte(model.getDefaultImageByte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage1Byte())) {
                command.setImage1Byte(model.getImage1Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage2Byte())) {
                command.setImage2Byte(model.getImage2Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage3Byte())) {
                command.setImage3Byte(model.getImage3Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage4Byte())) {
                command.setImage4Byte(model.getImage4Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage5Byte())) {
                command.setImage5Byte(model.getImage5Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage6Byte())) {
                command.setImage6Byte(model.getImage6Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage7Byte())) {
                command.setImage7Byte(model.getImage7Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage8Byte())) {
                command.setImage8Byte(model.getImage8Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage9Byte())) {
                command.setImage9Byte(model.getImage9Byte().getBytes());
            }
            if(!ObjectUtils.isEmpty(model.getImage10Byte())) {
                command.setImage10Byte(model.getImage10Byte().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setLastmodified(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Product.UPDATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteProduct(String productId) {

        DeleteProductCommand command = new DeleteProductCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setProductid(productId);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Product.DELETE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}