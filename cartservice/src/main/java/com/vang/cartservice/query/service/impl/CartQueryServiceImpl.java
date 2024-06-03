package com.vang.cartservice.query.service.impl;

import com.vang.cartservice.command.grpc.GetAuthCustomerClientImpl;
import com.vang.cartservice.command.grpc.GetCustomerIdByUsername;
import com.vang.cartservice.query.model.CartResponseModel;
import com.vang.cartservice.query.query.GetByCustomerId;
import com.vang.cartservice.query.service.CartQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartQueryServiceImpl implements CartQueryService {

    private final QueryGateway queryGateway;
    private final GetAuthCustomerClientImpl getAuthCustomerClient;
    private final GetCustomerIdByUsername getCustomerIdByUsername;

    public CartQueryServiceImpl(QueryGateway queryGateway, GetAuthCustomerClientImpl getAuthCustomerClient, GetCustomerIdByUsername getCustomerIdByUsername) {
        this.queryGateway = queryGateway;
        this.getAuthCustomerClient = getAuthCustomerClient;
        this.getCustomerIdByUsername = getCustomerIdByUsername;
    }

    @Override
    public ResponseEntity<List<CartResponseModel>> getAllByCustomer() {

        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        String customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        GetByCustomerId byCustomerId = new GetByCustomerId();
        byCustomerId.setCustomerId(customerId);
        List<CartResponseModel> models = queryGateway.query(byCustomerId, ResponseTypes.multipleInstancesOf(CartResponseModel.class)).join();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}