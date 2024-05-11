package com.vang.adminservice.command.service.impl;

import com.vang.adminservice.command.command.CreateAdminCommand;
import com.vang.adminservice.command.command.DeleteAdminCommand;
import com.vang.adminservice.command.command.UpdateAdminCommand;
import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import com.vang.adminservice.command.service.AdminCommandService;
import com.vang.adminservice.data.AdminsRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.*;
import org.vang.minimicroservice.method.DateUtils;
import org.vang.minimicroservice.method.MethodCommon;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminCommandServiceImpl implements AdminCommandService {

    private final CommandGateway commandGateway;
    private final AdminsRepository adminsRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminCommandServiceImpl(CommandGateway commandGateway, AdminsRepository adminsRepository) {
        this.commandGateway = commandGateway;
        this.adminsRepository = adminsRepository;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addAdmin(AdminRequestModel model) {
        long checkEmail = adminsRepository.getCountByEmail(model.getEmail());
        long checkPhone = adminsRepository.getCountByPhone(model.getPhone());
        Set<String> errors = new HashSet<>();
        int statusError = 0;
        ResponseCRUDCommon response;
        CreateAdminCommand command = new CreateAdminCommand();
        if(checkEmail > 0) {
            errors.add(MessageCommon.ERROR_002);
            statusError++;
        }
        if(checkPhone > 0) {
            errors.add(MessageCommon.ERROR_003);
            statusError++;
        }
        if(statusError > 0) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setRole(SecurityCommon.ROLE_ADMIN);
        command.setCreateddate(DateUtils.getDateTimeCurrent());
        command.setActivestatus(NumberUtils.ONE);
        command.setAvatar(ImageDefaultCommon.ADMIN_AVATAR_DEFAULT);
        command.setPassword(passwordEncoder.encode(model.getPassword()));
        command.setPasswordsecret(passwordEncoder.encode(model.getPasswordsecret()));
        commandGateway.sendAndWait(command);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Admin.CREATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateAdmin(UpdateAdminRequestModel model) {
        UpdateAdminCommand command = new UpdateAdminCommand();
        long checkEmail = adminsRepository.getCountByEmailToUpdate(model.getEmail(), model.getHdnOldEmail());
        long checkPhone = adminsRepository.getCountByPhoneToUpdate(model.getPhone(), model.getHdnOldPhone());
        Set<String> errors = new HashSet<>();
        ResponseCRUDCommon response;
        int statusError = 0;
        if(StringUtils.isBlank(model.getHdnOldPhone()) || StringUtils.isBlank(model.getHdnOldEmail())) {
            errors.add(MessageCommon.ERROR_004);
            errors.add(MessageCommon.ERROR_005);
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(checkEmail > 0) {
            errors.add(MessageCommon.ERROR_002);
            statusError++;
        }
        if(checkPhone > 0) {
            errors.add(MessageCommon.ERROR_003);
            statusError++;
        }
        if(statusError > 0) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(!ObjectUtils.isEmpty(model.getImage())) {
            try {
                command.setImage(model.getImage().getBytes());
                command.setFileName(model.getImage().getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!checkValidEncodePassword(model.getPassword())) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        if(!checkValidEncodePassword(model.getPasswordsecret())) {
            model.setPasswordsecret(passwordEncoder.encode(model.getPasswordsecret()));
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setPassword(passwordEncoder.encode(model.getPassword()));
        command.setPasswordsecret(passwordEncoder.encode(model.getPasswordsecret()));
        commandGateway.sendAndWait(command);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Admin.UPDATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteAdmin(String id) {
        DeleteAdminCommand command = new DeleteAdminCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setAdminid(id);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Admin.DELETE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean checkValidEncodePassword(String password) {

        return password.charAt(0) == '$' && checkCount(password) > 1;
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