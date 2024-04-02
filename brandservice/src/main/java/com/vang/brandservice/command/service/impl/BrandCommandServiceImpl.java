package com.vang.brandservice.command.service.impl;

import com.vang.brandservice.command.command.CreateBrandCommand;
import com.vang.brandservice.command.command.DeleteBrandCommand;
import com.vang.brandservice.command.command.UpdateBrandCommand;
import com.vang.brandservice.command.model.BrandRequestModel;
import com.vang.brandservice.command.service.BrandCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.vang.minimicroservice.message.MessageCode.*;

import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.method.MethodCommon;

import static org.vang.minimicroservice.service.BrandServiceCommon.*;

@Service
public class BrandCommandServiceImpl implements BrandCommandService {

    private final CommandGateway commandGateway;

    @Autowired
    public BrandCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<String> addBrand(BrandRequestModel model) {

        CreateBrandCommand command = new CreateBrandCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setActivestatus(NumberUtils.ONE);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(BRAND001, new String[] {BRAND, model.getBrandname()}), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateBrand(BrandRequestModel model) {

        UpdateBrandCommand command = new UpdateBrandCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setBrandid(model.getBrandid());
        command.setBrandname(model.getBrandname());
        command.setDescription(model.getDescription());
        command.setActivestatus(model.getActivestatus());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(BRAND002, new String[] {BRAND, model.getBrandname()}), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBrand(String id) {

        DeleteBrandCommand command = new DeleteBrandCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setBrandid(id);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(BRAND003, new String[] {BRAND}), HttpStatus.OK);
    }

}