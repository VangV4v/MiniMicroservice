package com.vang.addressservice.command.service;

import com.vang.addressservice.command.model.AddressRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface AddressCommandService {

    ResponseEntity<ResponseCRUDCommon> addAddress(AddressRequestModel model);
    ResponseEntity<ResponseCRUDCommon> updateAddress(AddressRequestModel model);
    ResponseEntity<ResponseCRUDCommon> deleteAddress(String addressId);
}