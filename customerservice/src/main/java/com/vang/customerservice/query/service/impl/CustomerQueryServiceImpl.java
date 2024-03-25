package com.vang.customerservice.query.service.impl;

import com.vang.customerservice.query.model.CustomerResponseModel;
import com.vang.customerservice.query.queries.GetAllCustomers;
import com.vang.customerservice.query.queries.GetByUserLogin;
import com.vang.customerservice.query.queries.GetDetailCustomer;
import com.vang.customerservice.query.service.CustomerQueryService;
import jakarta.persistence.EntityNotFoundException;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        if(model.isDataStatus()) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<List<CustomerResponseModel>> getAll() {

        GetAllCustomers allCustomers = new GetAllCustomers();
        List<CustomerResponseModel> listModel = queryGateway.query(allCustomers, ResponseTypes.multipleInstancesOf(CustomerResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponseModel> getByKeyLogin(String key) {

        CustomerResponseModel model = new CustomerResponseModel();
        GetByUserLogin userLogin = new GetByUserLogin();
        userLogin.setKey(key);
        model = queryGateway.query(userLogin, ResponseTypes.instanceOf(CustomerResponseModel.class)).join();
        if(model.isDataStatus()) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
    }
}