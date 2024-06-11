package com.vang.confirmorderservice.service;

import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface ConfirmOrderService {

    ResponseEntity<Object> getOrders();

    ResponseEntity<Object> getDetail(String orderId);

    ResponseEntity<Object> getAlls();

    ResponseEntity<ResponseCRUDCommon> approveOrder(String orderId);

    ResponseEntity<ResponseCRUDCommon> denyOrder(String orderId);
}