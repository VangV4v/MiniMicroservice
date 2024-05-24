package com.vang.sellerservice.command.service.impl;

import com.vang.sellerservice.command.command.CreateSellerCommand;
import com.vang.sellerservice.command.command.DeleteSellerCommand;
import com.vang.sellerservice.command.command.UpdateSellerCommand;
import com.vang.sellerservice.command.model.SellerRequestModel;
import com.vang.sellerservice.command.model.UpdateSellerRequestModel;
import com.vang.sellerservice.command.service.SellerCommandService;
import com.vang.sellerservice.data.SellersRepository;
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
import org.vang.minimicroservice.method.MethodCommon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class SellerCommandServiceImpl implements SellerCommandService {

    private final CommandGateway commandGateway;
    private final SellersRepository sellersRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public SellerCommandServiceImpl(CommandGateway commandGateway, SellersRepository sellersRepository) {
        this.commandGateway = commandGateway;
        this.sellersRepository = sellersRepository;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addSeller(SellerRequestModel model) {

        CreateSellerCommand command = new CreateSellerCommand();
        long checkCountUsername = sellersRepository.countByUsername(model.getUsername());
        long checkCountEmail = sellersRepository.countByEmail(model.getEmail());
        long checkCountPhone = sellersRepository.countByPhone(model.getPhone());
        ResponseCRUDCommon response;
        Set<String> errors = new HashSet<>();
        int statusCheckExits = 0;
        if(checkCountUsername > 0) {
            errors.add(MessageCommon.ERROR_001);
            statusCheckExits++;
        }
        if(checkCountEmail > 0) {
            errors.add(MessageCommon.ERROR_002);
            statusCheckExits++;
        }
        if(checkCountPhone > 0) {
            errors.add(MessageCommon.ERROR_003);
            statusCheckExits++;
        }
        if(statusCheckExits > 0) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setRole(SecurityCommon.ROLE_SELLER);
        command.setCreateddate(DateCommon.getDateTimeCurrent());
        command.setActivestatus(NumberUtils.ONE);
        command.setAvatar(ImageDefaultCommon.SELLER_AVATAR_DEFAULT);
        command.setPassword(passwordEncoder.encode(model.getPassword()));
        command.setDateofbirth(DateCommon.checkDateFormatAndConvert(model.getDateofbirth()));
        command.setShopnameexpiration(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Seller.CREATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateSeller(UpdateSellerRequestModel model) {

        UpdateSellerCommand command = new UpdateSellerCommand();
        long checkExistEmail = sellersRepository.countByEmailToUpdate(model.getEmail(), model.getHdnOldEmail());
        long checkExistPhone = sellersRepository.countByPhoneToUpdate(model.getPhone(), model.getHdnOldPhone());
        LocalDateTime shopNameExpiration = LocalDateTime.parse(model.getShopnameexpiration(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ResponseCRUDCommon response;
        int statusCheckData = 0;
        Set<String> errors = new HashSet<>();
        if(StringUtils.isBlank(model.getHdnOldEmail()) || StringUtils.isBlank(model.getHdnOldPhone())) {
            errors.add(MessageCommon.ERROR_004);
            errors.add(MessageCommon.ERROR_005);
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(errors).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(checkExistEmail > 0) {
            errors.add(MessageCommon.ERROR_002);
            statusCheckData++;
        }
        if(checkExistPhone > 0) {
            errors.add(MessageCommon.ERROR_003);
            statusCheckData++;
        }
        if(LocalDateTime.now().isBefore(shopNameExpiration.plusDays(30))) {
            errors.add(MessageCommon.ERROR_006);
            statusCheckData++;
        }
        if(statusCheckData > 0) {
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
        BeanUtils.copyProperties(model, command);
        command.setPassword(checkPassword(model.getPassword()));
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setRole(SecurityCommon.ROLE_SELLER);
        command.setDateofbirth(DateCommon.checkDateFormatAndConvert(model.getDateofbirth()));
        command.setLastmodified(DateCommon.getDateTimeCurrent());
        commandGateway.sendAndWait(command);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Seller.UPDATE_SUCCESSFUL).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteSeller(String sellerId) {

        DeleteSellerCommand command = new DeleteSellerCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setSellerid(sellerId);
        commandGateway.sendAndWait(command);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().message(MessageCommon.Seller.DELETE_SUCCESSFUL).errorStatus(false).build();
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