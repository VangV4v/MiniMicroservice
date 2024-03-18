package com.vang.customerservice.query.projection;

import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
import com.vang.customerservice.query.model.CustomerResponseModel;
import com.vang.customerservice.query.queries.GetAllCustomers;
import com.vang.customerservice.query.queries.GetDetailCustomer;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerQueryProjection {

    @Autowired
    private CustomersRepository repository;

    @QueryHandler
    public CustomerResponseModel getDetail(GetDetailCustomer detailCustomer) {

        Customers customers = repository.findById(detailCustomer.getId()).get();
        CustomerResponseModel model = new CustomerResponseModel();
        BeanUtils.copyProperties(customers, model);
        return model;
    }

    @QueryHandler
    public List<CustomerResponseModel> getAll(GetAllCustomers allCustomers) {

        List<Customers> listCustomers = repository.findAll();
        List<CustomerResponseModel> listModels = new ArrayList<>();
        listCustomers.forEach(e -> {
            CustomerResponseModel model = new CustomerResponseModel();
            BeanUtils.copyProperties(e, model);
            listModels.add(model);
        });
        return listModels;
    }

}