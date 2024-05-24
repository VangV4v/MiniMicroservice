package com.vang.sellerservice.command.controller;

import com.vang.sellerservice.command.model.SellerRequestModel;
import com.vang.sellerservice.command.model.UpdateSellerRequestModel;
import com.vang.sellerservice.command.service.SellerCommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/sellers/")
public class SellerCommandController {

    private final SellerCommandService sellerCommandService;

    @Autowired
    public SellerCommandController(@Valid SellerCommandService sellerCommandService) {
        this.sellerCommandService = sellerCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addSeller(@RequestBody @Valid SellerRequestModel model) {
        return sellerCommandService.addSeller(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateSeller(@ModelAttribute @Valid UpdateSellerRequestModel model) {
        return sellerCommandService.updateSeller(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteSeller(@PathVariable("id") String sellerId) {
        return sellerCommandService.deleteSeller(sellerId);
    }
}
