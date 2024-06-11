package com.vang.confirmorderservice.controller;

import com.vang.confirmorderservice.model.SendRequestModel;
import com.vang.confirmorderservice.service.ConfirmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/confirm-orders/")
public class ConfirmServiceController {

    private final ConfirmOrderService confirmOrderService;

    @Autowired
    public ConfirmServiceController(ConfirmOrderService confirmOrderService) {
        this.confirmOrderService = confirmOrderService;
    }

    @GetMapping("wait/")
    public ResponseEntity<Object> getOrderConfirm() {
        return confirmOrderService.getOrders();
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable("id") String id) {
        return confirmOrderService.getDetail(id);
    }

    @GetMapping("all/")
    public ResponseEntity<Object> getOrders() {
        return confirmOrderService.getAlls();
    }

    @PostMapping("approve/")
    public ResponseEntity<ResponseCRUDCommon> approveOrder(@RequestBody SendRequestModel model) {
        return confirmOrderService.approveOrder(model.getOrderId());
    }

    @PostMapping("deny/")
    public ResponseEntity<ResponseCRUDCommon> denyOrder(@RequestBody SendRequestModel model) {
        return confirmOrderService.denyOrder(model.getOrderId());
    }

}