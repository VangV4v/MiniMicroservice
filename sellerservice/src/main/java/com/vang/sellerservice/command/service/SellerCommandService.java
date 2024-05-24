package com.vang.sellerservice.command.service;

import com.vang.sellerservice.command.model.SellerRequestModel;
import com.vang.sellerservice.command.model.UpdateSellerRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface SellerCommandService {

    ResponseEntity<ResponseCRUDCommon> addSeller(SellerRequestModel model);

    ResponseEntity<ResponseCRUDCommon> updateSeller(UpdateSellerRequestModel model);

    ResponseEntity<ResponseCRUDCommon> deleteSeller(String sellerId);
}
