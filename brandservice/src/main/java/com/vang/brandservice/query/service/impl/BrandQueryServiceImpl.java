package com.vang.brandservice.query.service.impl;

import com.vang.brandservice.query.model.BrandResponseModel;
import com.vang.brandservice.query.queries.GetAllBrands;
import com.vang.brandservice.query.queries.GetDetailBrand;
import com.vang.brandservice.query.service.BrandQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandQueryServiceImpl implements BrandQueryService {

    @Autowired
    private QueryGateway queryGateway;

    @Override
    public ResponseEntity<BrandResponseModel> getDetail(String id) {

        GetDetailBrand detailBrand = new GetDetailBrand();
        detailBrand.setId(id);
        BrandResponseModel model = queryGateway.query(detailBrand, ResponseTypes.instanceOf(BrandResponseModel.class)).join();
        if(model.isDataStatus()) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<BrandResponseModel>> getAll() {

        GetAllBrands allBrands = new GetAllBrands();
        List<BrandResponseModel> listModel = queryGateway.query(allBrands, ResponseTypes.multipleInstancesOf(BrandResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }

}
