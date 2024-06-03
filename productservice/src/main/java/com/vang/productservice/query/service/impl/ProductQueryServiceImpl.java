package com.vang.productservice.query.service.impl;

import com.vang.productservice.query.model.ProductResponseModel;
import com.vang.productservice.query.queries.GetAllBySellerId;
import com.vang.productservice.query.queries.GetAllProducts;
import com.vang.productservice.query.queries.GetProductById;
import com.vang.productservice.query.service.ProductQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public ProductQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<ProductResponseModel> getProductById(String productId) {

        GetProductById productById = new GetProductById();
        productById.setProductId(productId);
        ProductResponseModel model = queryGateway.query(productById, ResponseTypes.instanceOf(ProductResponseModel.class)).join();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponseModel>> getProductBySeller(String sellerId) {

        GetAllBySellerId allBySellerId = new GetAllBySellerId();
        allBySellerId.setSellerid(sellerId);
        List<ProductResponseModel> listModels = queryGateway.query(allBySellerId, ResponseTypes.multipleInstancesOf(ProductResponseModel.class)).join();
        return new ResponseEntity<>(listModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponseModel>> getAllProducts() {

        GetAllProducts allProducts = new GetAllProducts();
        List<ProductResponseModel> models = queryGateway.query(allProducts, ResponseTypes.multipleInstancesOf(ProductResponseModel.class)).join();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}