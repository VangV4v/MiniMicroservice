package com.vang.cartservice.command.service;

import com.vang.cartservice.command.model.CartRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface CartCommandService {
    ResponseEntity<ResponseCRUDCommon> addCart(CartRequestModel model);

    ResponseEntity<ResponseCRUDCommon> updateCart(CartRequestModel model);

    ResponseEntity<ResponseCRUDCommon> deleteItem(String cartId);

    ResponseEntity<ResponseCRUDCommon> deleteAll();
}
