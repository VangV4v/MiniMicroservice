package com.vang.sellerservice.query.service.impl;

import com.vang.sellerservice.query.model.SellerResponseModel;
import com.vang.sellerservice.query.queries.GetAllSellers;
import com.vang.sellerservice.query.queries.GetBySellerId;
import com.vang.sellerservice.query.service.SellerQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerQueryServiceImpl implements SellerQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public SellerQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<SellerResponseModel> getBySellerId(String sellerId) {
        GetBySellerId bySellerId = new GetBySellerId();
        bySellerId.setSellerId(sellerId);
        SellerResponseModel model = queryGateway.query(bySellerId, ResponseTypes.instanceOf(SellerResponseModel.class)).join();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SellerResponseModel>> getAllSellers() {

        GetAllSellers allSellers = new GetAllSellers();
        List<SellerResponseModel> models = queryGateway.query(allSellers, ResponseTypes.multipleInstancesOf(SellerResponseModel.class)).join();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}