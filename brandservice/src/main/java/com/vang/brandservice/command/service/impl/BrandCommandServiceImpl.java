package com.vang.brandservice.command.service.impl;

import com.vang.brandservice.command.command.CreateBrandCommand;
import com.vang.brandservice.command.command.DeleteBrandCommand;
import com.vang.brandservice.command.command.UpdateBrandCommand;
import com.vang.brandservice.command.model.BrandRequestModel;
import com.vang.brandservice.command.service.BrandCommandService;
import com.vang.brandservice.data.BrandsRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BrandCommandServiceImpl implements BrandCommandService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private BrandsRepository repository;

    @Override
    public ResponseEntity<String> addBrand(BrandRequestModel model) {

        CreateBrandCommand command = new CreateBrandCommand();
        System.out.println("----------------------->"+repository.generateId());
        command.setAutoAggregateIdentifier(System.currentTimeMillis()*12);
        command.setBrandid(repository.generateId());
        System.out.println("Brand ID "+command.getBrandid());
        command.setBrandname(model.getBrandname());
        command.setDescription(model.getDescription());
        command.setActivestatus(1);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("add Brand '"+model.getBrandname()+"'", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateBrand(BrandRequestModel model) {

        UpdateBrandCommand command = new UpdateBrandCommand();
        command.setAutoAggregateIdentifier(System.currentTimeMillis()*12);
        command.setBrandid(model.getBrandid());
        command.setBrandname(model.getBrandname());
        command.setDescription(model.getDescription());
        command.setActivestatus(model.getActivestatus());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("update Brand '"+model.getBrandname()+"'", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBrand(String id) {

        DeleteBrandCommand command = new DeleteBrandCommand();
        command.setAutoAggregateIdentifier(System.currentTimeMillis()*12);
        command.setBrandid(id);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>("delete Brand successfully", HttpStatus.OK);
    }

}