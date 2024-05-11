package com.vang.categoryservice.query.service.impl;

import com.vang.categoryservice.query.model.CategoryResponseModel;
import com.vang.categoryservice.query.queries.GetAllCategories;
import com.vang.categoryservice.query.queries.GetByCategoryId;
import com.vang.categoryservice.query.service.CategoryQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public CategoryQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<CategoryResponseModel> getByCategoryId(String categoryId) {

        GetByCategoryId getByCategoryId = new GetByCategoryId();
        getByCategoryId.setCategoryId(categoryId);
        CategoryResponseModel model = queryGateway.query(getByCategoryId, ResponseTypes.instanceOf(CategoryResponseModel.class)).join();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryResponseModel>> getAllCategories() {

        GetAllCategories getAllCategories = new GetAllCategories();
        List<CategoryResponseModel> listModel = queryGateway.query(getAllCategories, ResponseTypes.multipleInstancesOf(CategoryResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }
}
