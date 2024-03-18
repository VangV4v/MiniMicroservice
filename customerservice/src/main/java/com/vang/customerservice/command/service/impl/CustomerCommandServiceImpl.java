package com.vang.customerservice.command.service.impl;

import com.vang.customerservice.command.command.CreateCustomerCommand;
import com.vang.customerservice.command.command.DeleteCustomerCommand;
import com.vang.customerservice.command.command.UpdateCustomerCommand;
import com.vang.customerservice.command.model.CustomerRequestModel;
import com.vang.customerservice.command.service.CustomerCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.vang.minimicroservice.message.MessageCode.*;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.method.DateUtils;
import org.vang.minimicroservice.method.MethodCommon;

import static org.vang.minimicroservice.service.CustomerServiceCommon.*;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public ResponseEntity<String> addCustomer(CustomerRequestModel model) {

        CreateCustomerCommand command = new CreateCustomerCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setRole(ROLE_CUSTOMER);
        command.setCreateddate(DateUtils.getDateTimeCurrent());
        command.setActivestatus(1);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(CUSTOMER001, new String[]{CUSTOMER, model.getUsername()}), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCustomer(CustomerRequestModel model) {

        UpdateCustomerCommand command = new UpdateCustomerCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(CUSTOMER002, new String[]{CUSTOMER, model.getUsername()}), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCustomer(String id) {

        DeleteCustomerCommand command = new DeleteCustomerCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(id);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(MessageCommon.getMessage(CUSTOMER003, new String[]{CUSTOMER}), HttpStatus.OK);
    }

}