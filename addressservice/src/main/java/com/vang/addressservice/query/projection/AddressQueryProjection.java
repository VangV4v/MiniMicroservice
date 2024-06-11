package com.vang.addressservice.query.projection;

import com.vang.addressservice.data.Addresses;
import com.vang.addressservice.data.AddressesRepository;
import com.vang.addressservice.query.model.AddressResponseModel;
import com.vang.addressservice.query.queries.GetByCustomerID;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressQueryProjection {

    private final AddressesRepository addressesRepository;

    @Autowired
    public AddressQueryProjection(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @QueryHandler
    public List<AddressResponseModel> getAllByCustomer(GetByCustomerID byCustomerID) {

        List<Addresses> listAddresses = addressesRepository.findAllByCustomerId(byCustomerID.getCustomerId());
        List<AddressResponseModel> listModel = new ArrayList<>();
        listAddresses.forEach(e -> {
            AddressResponseModel model = new AddressResponseModel();
            BeanUtils.copyProperties(e, model);
            listModel.add(model);
        });
        return listModel;
    }
}