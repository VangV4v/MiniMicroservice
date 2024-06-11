package com.vang.cartservice.command.service.impl;

import com.google.gson.Gson;
import com.vang.cartservice.command.command.CreateCartCommand;
import com.vang.cartservice.command.command.DeleteAllCartCommand;
import com.vang.cartservice.command.command.DeleteCartCommand;
import com.vang.cartservice.command.command.UpdateCartCommand;
import com.vang.cartservice.command.grpc.GetAuthCustomerClientImpl;
import com.vang.cartservice.command.grpc.GetCustomerIdByUsername;
import com.vang.cartservice.command.grpc.GetProductByIdClientImpl;
import com.vang.cartservice.command.grpcmodel.ProductJsonModel;
import com.vang.cartservice.command.model.CartRequestModel;
import com.vang.cartservice.command.service.CartCommandService;
import com.vang.cartservice.data.Carts;
import com.vang.cartservice.data.CartsRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Service
public class CartCommandServiceImpl implements CartCommandService {

    private final CommandGateway commandGateway;
    private final GetAuthCustomerClientImpl getAuthCustomerClient;
    private final GetCustomerIdByUsername getCustomerIdByUsername;
    private final GetProductByIdClientImpl getProductByIdClient;
    private final CartsRepository cartsRepository;

    @Autowired
    public CartCommandServiceImpl(CommandGateway commandGateway, GetAuthCustomerClientImpl getAuthCustomerClient, CartsRepository cartsRepository,
                                  GetCustomerIdByUsername getCustomerIdByUsername, GetProductByIdClientImpl getProductByIdClient) {
        this.commandGateway = commandGateway;
        this.getCustomerIdByUsername = getCustomerIdByUsername;
        this.getAuthCustomerClient = getAuthCustomerClient;
        this.getProductByIdClient = getProductByIdClient;
        this.cartsRepository = cartsRepository;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> addCart(CartRequestModel model) {

        Gson gson = new Gson();
        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        String customerId,productDetail;
        ProductJsonModel productJsonModel;
        Carts checkExist;
        int quantity;
        CreateCartCommand command = new CreateCartCommand();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        productDetail = getProductByIdClient.getProductById(model.getProductid());
        quantity = model.getQuantity() == 0 ? 1 : model.getQuantity();
        productJsonModel = gson.fromJson(productDetail, ProductJsonModel.class);
        checkExist = cartsRepository.findByCustomerAndProduct(customerId, productJsonModel.getProductid());
        if(!ObjectUtils.isEmpty(checkExist)) {
            model.setCartid(checkExist.getCartid());
            model.setQuantity(model.getQuantity() + checkExist.getQuantity());
            model.setCustomerid(customerId);
            model.setProductid(productJsonModel.getProductid());
            model.setProductdetail(productDetail);
            return updateCart(model);
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(customerId);
        command.setProductid(productJsonModel.getProductid());
        command.setProductdetail(productDetail);
        command.setQuantity(quantity);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.CREATE_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> updateCart(CartRequestModel model) {
        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        UpdateCartCommand command = new UpdateCartCommand();
        ResponseCRUDCommon response;
        if(StringUtils.isEmpty(getInfoAuth)) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.CREATE_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteItem(String cartId) {
        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        ResponseCRUDCommon response;
        DeleteCartCommand command = new DeleteCartCommand();
        if(StringUtils.isEmpty(getInfoAuth)) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCartid(cartId);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.DELETE_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteAll() {
        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        DeleteAllCartCommand command = new DeleteAllCartCommand();
        String customerId;
        ResponseCRUDCommon response;
        if(StringUtils.isEmpty(getInfoAuth)) {
            response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(customerId);
        response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.DELETE_ALL_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}