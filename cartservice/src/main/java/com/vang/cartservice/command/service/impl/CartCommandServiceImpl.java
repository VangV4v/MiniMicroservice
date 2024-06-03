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
        String customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        String productDetail = getProductByIdClient.getProductById(model.getProductid());
        int quantity = model.getQuantity() == 0 ? 1 : model.getQuantity();
        ProductJsonModel productJsonModel = gson.fromJson(productDetail, ProductJsonModel.class);
        Carts checkExist = cartsRepository.findByCustomerAndProduct(customerId, productJsonModel.getProductid());
        if(!ObjectUtils.isEmpty(checkExist)) {
            model.setCartid(checkExist.getCartid());
            model.setQuantity(model.getQuantity() + checkExist.getQuantity());
            model.setCustomerid(customerId);
            model.setProductid(productJsonModel.getProductid());
            model.setProductdetail(productDetail);
            return updateCart(model);
        }
        CreateCartCommand command = new CreateCartCommand();
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
        UpdateCartCommand command = new UpdateCartCommand();
        BeanUtils.copyProperties(model, command);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.CREATE_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteItem(String cartId) {
        DeleteCartCommand command = new DeleteCartCommand();
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCartid(cartId);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.DELETE_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> deleteAll() {
        DeleteAllCartCommand command = new DeleteAllCartCommand();
        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        String customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        command.setAutoAggregateIdentifier(MethodCommon.generateAggregateIdentifier());
        command.setCustomerid(customerId);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message(MessageCommon.Cart.DELETE_ALL_SUCCESSFUL).build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}