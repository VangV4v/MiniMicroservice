package com.vang.addressservice.command.controller;

import com.vang.addressservice.command.model.AddressRequestModel;
import com.vang.addressservice.command.service.AddressCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/addresses/")
public class AddressCommandController {

    private final AddressCommandService addressCommandService;

    @Autowired
    public AddressCommandController(AddressCommandService addressCommandService) {
        this.addressCommandService = addressCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addAddress(@RequestBody AddressRequestModel model) {
        return addressCommandService.addAddress(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateAddress(@RequestBody AddressRequestModel model) {
        return addressCommandService.updateAddress(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteAddress(@PathVariable("id") String id) {
        return addressCommandService.deleteAddress(id);
    }
}