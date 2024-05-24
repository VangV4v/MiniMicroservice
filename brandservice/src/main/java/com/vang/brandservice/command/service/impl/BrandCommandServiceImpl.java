package com.vang.brandservice.command.service.impl;

import com.google.protobuf.ByteString;
import com.vang.brandservice.command.command.CreateBrandCommand;
import com.vang.brandservice.command.command.DeleteBrandCommand;
import com.vang.brandservice.command.command.UpdateBrandCommand;
import com.vang.brandservice.command.grpc.ImageServiceClientImpl;
import com.vang.brandservice.command.model.BrandRequestModel;
import com.vang.brandservice.command.service.BrandCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.vang.minimicroservice.message.MessageCode.*;

import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.DateCommon;
import org.vang.minimicroservice.common.ImageDefaultCommon;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

import java.io.IOException;

import static org.vang.minimicroservice.service.BrandServiceCommon.*;

@Service
public class BrandCommandServiceImpl implements BrandCommandService {

    private final CommandGateway commandGateway;

    private final ImageServiceClientImpl imageServiceClient;

    @Autowired
    public BrandCommandServiceImpl(CommandGateway commandGateway, ImageServiceClientImpl imageServiceClient) {
        this.imageServiceClient = imageServiceClient;
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addBrand(BrandRequestModel model) {

        CreateBrandCommand command = new CreateBrandCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setActivestatus(NumberUtils.ONE);
        command.setLogo(ImageDefaultCommon.BRAND_LOGO_DEFAULT);
        command.setCreateddate(DateCommon.getDateTimeCurrent());
        if(!ObjectUtils.isEmpty(model.getImage())) {
            try {
                command.setImage(model.getImage().getBytes());
                command.setFileName(model.getImage().getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(ADD_BRAND_SUCCESS).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateBrand(BrandRequestModel model) {

        UpdateBrandCommand command = new UpdateBrandCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setLastmodified(DateCommon.getDateTimeCurrent());
        if(!ObjectUtils.isEmpty(model.getImage())) {
            try {
                command.setImage(model.getImage().getBytes());
                command.setFileName(model.getImage().getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(UPDATE_BRAND_SUCCESS).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteBrand(String id) {

        DeleteBrandCommand command = new DeleteBrandCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setBrandid(id);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(DELETE_BRAND_SUCCESS).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}