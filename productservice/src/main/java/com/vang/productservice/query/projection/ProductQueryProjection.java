package com.vang.productservice.query.projection;

import com.google.gson.Gson;
import com.vang.productservice.command.grpcmodel.BrandJsonModel;
import com.vang.productservice.command.grpcmodel.CategoryJsonModel;
import com.vang.productservice.data.Products;
import com.vang.productservice.data.ProductsRepository;
import com.vang.productservice.query.model.ProductResponseModel;
import com.vang.productservice.query.queries.GetAllBySellerId;
import com.vang.productservice.query.queries.GetAllProducts;
import com.vang.productservice.query.queries.GetProductById;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryProjection {

    private final ProductsRepository productsRepository;
    private final Gson gson = new Gson();

    @Autowired
    public ProductQueryProjection(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public ProductResponseModel getProductById(GetProductById productById) {

        Products products = productsRepository.findById(productById.getProductId()).orElse(new Products());
        ProductResponseModel model = new ProductResponseModel();
        if(StringUtils.isBlank(products.getProductid())) {
            model.initialize();
        } else {
            BeanUtils.copyProperties(products, model);
            BrandJsonModel brandJsonModel = gson.fromJson(products.getBranddetail(), BrandJsonModel.class);
            CategoryJsonModel categoryJsonModel = gson.fromJson(products.getCategorydetail(), CategoryJsonModel.class);
            model.setBranddetail(brandJsonModel);
            model.setCategorydetail(categoryJsonModel);
        }
        return model;
    }

    @QueryHandler
    public List<ProductResponseModel> getProductBySeller(GetAllBySellerId allBySellerId) {

        List<Products> products = productsRepository.getAllBySellerId(allBySellerId.getSellerid());
        List<ProductResponseModel> productResponseModels = new ArrayList<ProductResponseModel>();
        products.forEach(e -> {
            ProductResponseModel model = new ProductResponseModel();
            BeanUtils.copyProperties(e, model);
            BrandJsonModel brandJsonModel = gson.fromJson(e.getBranddetail(), BrandJsonModel.class);
            CategoryJsonModel categoryJsonModel = gson.fromJson(e.getCategorydetail(), CategoryJsonModel.class);
            model.setBranddetail(brandJsonModel);
            model.setCategorydetail(categoryJsonModel);
            productResponseModels.add(model);
        });
        return productResponseModels;
    }

    @QueryHandler
    public List<ProductResponseModel> getAllProducts(GetAllProducts allProducts) {

        List<Products> products = productsRepository.findAll();
        List<ProductResponseModel> productResponseModels = new ArrayList<ProductResponseModel>();
        products.forEach(e -> {
            ProductResponseModel model = new ProductResponseModel();
            BeanUtils.copyProperties(e, model);
            BrandJsonModel brandJsonModel = gson.fromJson(e.getBranddetail(), BrandJsonModel.class);
            CategoryJsonModel categoryJsonModel = gson.fromJson(e.getCategorydetail(), CategoryJsonModel.class);
            model.setBranddetail(brandJsonModel);
            model.setCategorydetail(categoryJsonModel);
            productResponseModels.add(model);
        });
        return productResponseModels;
    }
}