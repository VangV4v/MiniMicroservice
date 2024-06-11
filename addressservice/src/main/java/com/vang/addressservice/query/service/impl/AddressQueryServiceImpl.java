package com.vang.addressservice.query.service.impl;

import com.vang.addressservice.command.grpc.GetAuthCustomerClientImpl;
import com.vang.addressservice.command.grpc.GetCustomerIdByUsername;
import com.vang.addressservice.query.model.AddressResponseModel;
import com.vang.addressservice.query.queries.GetByCustomerID;
import com.vang.addressservice.query.service.AddressQueryService;
import org.apache.commons.lang.StringUtils;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

import java.util.List;

@Service
public class AddressQueryServiceImpl implements AddressQueryService {

    private final QueryGateway queryGateway;
    private final GetAuthCustomerClientImpl getAuthCustomerClient;
    private final GetCustomerIdByUsername getCustomerIdByUsername;

    @Autowired
    public AddressQueryServiceImpl(QueryGateway queryGateway, GetAuthCustomerClientImpl getAuthCustomerClient, GetCustomerIdByUsername getCustomerIdByUsername) {
        this.queryGateway = queryGateway;
        this.getAuthCustomerClient = getAuthCustomerClient;
        this.getCustomerIdByUsername = getCustomerIdByUsername;
    }

    @Override
    public ResponseEntity<Object> getAllByCustomerID() {

        GetByCustomerID byCustomerID = new GetByCustomerID();
        String customerId;
        String getInfoAuth = getAuthCustomerClient.getAuthCustomer();
        if(StringUtils.isEmpty(getInfoAuth)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        customerId = getCustomerIdByUsername.getCustomerIdByUsername(getInfoAuth);
        byCustomerID.setCustomerId(customerId);
        List<AddressResponseModel> listModel = queryGateway.query(byCustomerID, ResponseTypes.multipleInstancesOf(AddressResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }
}
