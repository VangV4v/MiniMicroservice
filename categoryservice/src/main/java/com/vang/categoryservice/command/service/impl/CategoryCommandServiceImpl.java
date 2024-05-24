package com.vang.categoryservice.command.service.impl;

import com.vang.categoryservice.command.command.CreateCategoryCommand;
import com.vang.categoryservice.command.command.DeleteCategoryCommand;
import com.vang.categoryservice.command.command.UpdateCategoryCommand;
import com.vang.categoryservice.command.model.CategoryRequestModel;
import com.vang.categoryservice.command.service.CategoryCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.DateCommon;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CommandGateway commandGateway;

    @Autowired
    public CategoryCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addCategory(CategoryRequestModel model) {
        CreateCategoryCommand command = new CreateCategoryCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCreateddate(DateCommon.getDateTimeCurrent());
        command.setActivestatus(NumberUtils.ONE);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Category.CREATE_SUCCESSFUL).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateCategory(CategoryRequestModel model) {
        UpdateCategoryCommand command = new UpdateCategoryCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setLastmodified(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Category.CREATE_SUCCESSFUL).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteCategory(String categoryId) {
        DeleteCategoryCommand command = new DeleteCategoryCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCategoryid(categoryId);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Category.CREATE_SUCCESSFUL).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}