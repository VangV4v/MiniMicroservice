package com.vang.customerservice.command.service.impl;

import com.vang.customerservice.command.command.CreateCustomerCommand;
import com.vang.customerservice.command.command.DeleteCustomerCommand;
import com.vang.customerservice.command.command.UpdateCustomerCommand;
import com.vang.customerservice.command.grpc.ImageServiceClientImpl;
import com.vang.customerservice.command.model.CustomerRequestModel;
import com.vang.customerservice.command.model.CustomerUpdateRequestModel;
import com.vang.customerservice.command.service.CustomerCommandService;
import com.vang.customerservice.data.CustomersRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static org.vang.minimicroservice.message.MessageCode.*;

import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.ImageDefaultCommon;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.common.StringCommon;
import org.vang.minimicroservice.method.DateUtils;
import org.vang.minimicroservice.method.MethodCommon;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.vang.minimicroservice.service.CustomerServiceCommon.*;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CommandGateway commandGateway;

    private final CustomersRepository customersRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public CustomerCommandServiceImpl(CustomersRepository customersRepository, CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        this.customersRepository = customersRepository;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addCustomer(CustomerRequestModel model) {

        long checkUsername = customersRepository.countByUsername(model.getUsername());
        long checkEmail = customersRepository.countByEmail(model.getEmail());
        long checkPhone = customersRepository.countByPhone(model.getPhone());
        CreateCustomerCommand command = new CreateCustomerCommand();
        ResponseCRUDCommon response;
        int countError = 0;
        Set<String> errors = new HashSet<>();
        if (checkUsername > 0) {

            countError++;
            errors.add(Message.ERROR_CUSTOMER_001);
        }
        if(checkPhone > 0) {

            countError++;
            errors.add(Message.ERROR_CUSTOMER_003);
        }
        if (checkEmail > 0) {

            countError++;
            errors.add(Message.ERROR_CUSTOMER_002);
        }
        if(countError > 0) {

            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {

            BeanUtils.copyProperties(model, command);
            command.setPassword(passwordEncoder.encode(command.getPassword()));
            command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
            command.setRole(ROLE_CUSTOMER);
            command.setCreateddate(DateUtils.getDateTimeCurrent());
            command.setActivestatus(1);
            command.setAvatar(ImageDefaultCommon.CUSTOMER_AVATAR_DEFAULT);
            commandGateway.sendAndWait(command);
            response = ResponseCRUDCommon.builder().errorStatus(false).message(Message.ADD_CUSTOMER_SUCCESS).build();
            try {
                Thread.sleep(4);
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateCustomer(CustomerUpdateRequestModel model) {

        long checkEmailExist = customersRepository.countByEmailForUpdate(model.getEmail(), model.getHdnOldEmail());
        long checkPhoneExist = customersRepository.countByPhoneForUpdate(model.getPhone(), model.getHdnOldPhone());
        UpdateCustomerCommand command = new UpdateCustomerCommand();
        int statusCheckData = 0;
        int statusCheckRequest = 0;
        ResponseCRUDCommon response;
        Set<String> errors = new HashSet<>();
        if(!StringUtils.isBlank(model.getEmail()) && StringUtils.isBlank(model.getHdnOldEmail())) {
            statusCheckRequest++;
            errors.add(Message.ERROR_CUSTOMER_004);
        }
        if(!StringUtils.isBlank(model.getPhone()) && StringUtils.isBlank(model.getHdnOldPhone())) {
            statusCheckRequest++;
            errors.add(Message.ERROR_CUSTOMER_005);
        }
        if(statusCheckRequest > 0) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(checkEmailExist > 0) {

            errors.add(Message.ERROR_CUSTOMER_002);
            statusCheckData++;
        }
        if(!ObjectUtils.isEmpty(model.getImage())) {
            try {
                command.setImage(model.getImage().getBytes());
                command.setFileName(model.getImage().getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(checkPhoneExist > 0) {

            errors.add(Message.ERROR_CUSTOMER_003);
            statusCheckData++;
        }
        if(statusCheckData > 0) {

            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {

            BeanUtils.copyProperties(model, command);
            command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
            command.setPassword(checkPassword(model.getPassword()));
            commandGateway.sendAndWait(command);
            response = ResponseCRUDCommon.builder().errorStatus(false).message(Message.UPDATE_CUSTOMER_SUCCESS).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteCustomer(String id) {

        DeleteCustomerCommand command = new DeleteCustomerCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(id);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(Message.DELETE_CUSTOMER_SUCCESS).errorStatus(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String checkPassword(String password) {
        if(password.startsWith(StringCommon.DOLLAR_SIGN) && checkCount(password) > 1) {
            return password;
        }
        return passwordEncoder.encode(password);
    }

    private int checkCount(String password) {
        int count = 0;
        for (String str : password.split("")) {
            if(str.equals(StringCommon.DOLLAR_SIGN)) {
                count++;
            }
        }
        return count;
    }

}