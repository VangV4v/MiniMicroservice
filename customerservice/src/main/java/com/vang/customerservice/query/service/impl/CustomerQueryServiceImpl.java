package com.vang.customerservice.query.service.impl;

import com.vang.customerservice.query.model.CustomerResponseModel;
import com.vang.customerservice.query.queries.GetAllCustomers;
import com.vang.customerservice.query.queries.GetDetailCustomer;
import com.vang.customerservice.query.service.CustomerQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    @Autowired
    private QueryGateway queryGateway;

    @Override
    public ResponseEntity<CustomerResponseModel> getDetail(String id) {

        GetDetailCustomer detailCustomer = new GetDetailCustomer();
        detailCustomer.setId(id);
        CustomerResponseModel model = queryGateway.query(detailCustomer, ResponseTypes.instanceOf(CustomerResponseModel.class)).join();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerResponseModel>> getAll() {

        GetAllCustomers allCustomers = new GetAllCustomers();
        List<CustomerResponseModel> listModel = queryGateway.query(allCustomers, ResponseTypes.multipleInstancesOf(CustomerResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }
}