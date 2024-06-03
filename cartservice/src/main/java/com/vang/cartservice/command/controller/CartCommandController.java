package com.vang.cartservice.command.controller;

import com.vang.cartservice.command.model.CartRequestModel;
import com.vang.cartservice.command.service.CartCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/carts/")
@CrossOrigin
public class CartCommandController {

   private final CartCommandService cartCommandService;

   @Autowired
   public CartCommandController(CartCommandService cartCommandService) {
       this.cartCommandService = cartCommandService;
   }

   @PostMapping
   public ResponseEntity<ResponseCRUDCommon> addCart(@RequestBody CartRequestModel model) {
        return cartCommandService.addCart(model);
   }

   @PutMapping
   public ResponseEntity<ResponseCRUDCommon> updateCart(@RequestBody CartRequestModel model) {
       return cartCommandService.updateCart(model);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<ResponseCRUDCommon> deleteItem(@PathVariable("id") String cartId) {
       return cartCommandService.deleteItem(cartId);
   }

   @DeleteMapping
   public ResponseEntity<ResponseCRUDCommon> deleteAll() {
       return cartCommandService.deleteAll();
   }

}