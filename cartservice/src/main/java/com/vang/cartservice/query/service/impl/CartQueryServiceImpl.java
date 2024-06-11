package com.vang.cartservice.query.service.impl;

import com.vang.cartservice.command.grpc.GetAuthCustomerClientImpl;
import com.vang.cartservice.command.grpc.GetCustomerIdByUsername;
import com.vang.cartservice.query.model.CartResponseModel;
import com.vang.cartservice.query.query.GetByCustomerId;
import com.vang.cartservice.query.service.CartQueryService;
import org.apache.commons.lang.StringUtils;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

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
    public ResponseEntity<Object> getAllByCustomer() {

        String getInfoAuth = getAuthCustomerClient.getAuthInfomation();
        String customerId;
        GetByCustomerId byCustomerId = new GetByCustomerId();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        customerId = getCustomerIdByUsername.getCustomerId(getInfoAuth);
        byCustomerId.setCustomerId(customerId);
        List<CartResponseModel> models = queryGateway.query(byCustomerId, ResponseTypes.multipleInstancesOf(CartResponseModel.class)).join();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}