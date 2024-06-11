package com.vang.addressservice.command.service.impl;

import com.vang.addressservice.command.command.CreateAddressCommand;
import com.vang.addressservice.command.command.DeleteAddressCommand;
import com.vang.addressservice.command.command.UpdateAddressCommand;
import com.vang.addressservice.command.grpc.GetAuthCustomerClientImpl;
import com.vang.addressservice.command.grpc.GetCustomerIdByUsername;
import com.vang.addressservice.command.model.AddressRequestModel;
import com.vang.addressservice.command.service.AddressCommandService;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.DateCommon;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Service
public class AddressCommandServiceImpl implements AddressCommandService {

    private final CommandGateway commandGateway;
    private final GetAuthCustomerClientImpl getAuthCustomerClient;
    private final GetCustomerIdByUsername getCustomerIdByUsername;

    @Autowired
    public AddressCommandServiceImpl(CommandGateway commandGateway, GetAuthCustomerClientImpl getAuthCustomerClient, GetCustomerIdByUsername getCustomerIdByUsername) {
        this.commandGateway = commandGateway;
        this.getAuthCustomerClient = getAuthCustomerClient;
        this.getCustomerIdByUsername = getCustomerIdByUsername;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addAddress(AddressRequestModel model) {
        CreateAddressCommand command = new CreateAddressCommand();
        String customerId;
        String getInfoAuth = getAuthCustomerClient.getAuthCustomer();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        customerId = getCustomerIdByUsername.getCustomerIdByUsername(getInfoAuth);
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(customerId);
        command.setCreateddate(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).message(MessageCommon.Address.CREATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateAddress(AddressRequestModel model) {
        UpdateAddressCommand command = new UpdateAddressCommand();
        String getInfoAuth = getAuthCustomerClient.getAuthCustomer();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setLastmodified(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).message(MessageCommon.Address.UPDATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteAddress(String addressId) {
        DeleteAddressCommand command = new DeleteAddressCommand();
        String getInfoAuth = getAuthCustomerClient.getAuthCustomer();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setAddressid(addressId);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).message(MessageCommon.Address.DELETE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}