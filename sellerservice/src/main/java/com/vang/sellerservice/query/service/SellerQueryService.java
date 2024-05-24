package com.vang.sellerservice.query.service;

import com.vang.sellerservice.query.model.SellerResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SellerQueryService {

    ResponseEntity<SellerResponseModel> getBySellerId(String sellerId);

    ResponseEntity<List<SellerResponseModel>> getAllSellers();
}