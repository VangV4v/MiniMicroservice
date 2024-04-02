package com.vang.adminservice.command.service.impl;

import com.vang.adminservice.command.command.CreateAdminCommand;
import com.vang.adminservice.command.command.DeleteAdminCommand;
import com.vang.adminservice.command.command.UpdateAdminCommand;
import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.service.AdminCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.SecurityCommon;
import org.vang.minimicroservice.method.DateUtils;
import org.vang.minimicroservice.method.MethodCommon;

@Service
public class AdminCommandServiceImpl implements AdminCommandService {

    private final CommandGateway commandGateway;

    @Autowired
    public AdminCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<String> addAdmin(AdminRequestModel model) {
        CreateAdminCommand command = new CreateAdminCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setRole(SecurityCommon.ROLE_ADMIN);
        command.setCreateddate(DateUtils.getDateTimeCurrent());
        command.setActivestatus(NumberUtils.ZERO);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("add Admin success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAdmin(AdminRequestModel model) {
        UpdateAdminCommand command = new UpdateAdminCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("update Admin success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(String id) {
        DeleteAdminCommand command = new DeleteAdminCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setAdminid(id);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("delete Admin success", HttpStatus.OK);
    }
}