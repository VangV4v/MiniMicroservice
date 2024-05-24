package com.vang.productservice.command.event;

import com.vang.productservice.command.grpc.ImageGrpcClient;
import com.vang.productservice.command.grpcmodel.ProductImageJsonModel;
import com.vang.productservice.data.Products;
import com.vang.productservice.data.ProductsRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;
    private final ImageGrpcClient imageGrpcClient;

    @Autowired
    public ProductEventsHandler(ProductsRepository productsRepository, ImageGrpcClient imageGrpcClient) {
        this.productsRepository = productsRepository;
        this.imageGrpcClient = imageGrpcClient;
    }

    @EventHandler
    public void handle(ProductCreatedEvent event) {
        event.setProductid(autoGenerateProductId());
        Products products = new Products();
        BeanUtils.copyProperties(event, products);
        productsRepository.save(products);
        ProductImageJsonModel jsonModel = new ProductImageJsonModel();
        BeanUtils.copyProperties(event, jsonModel);
        jsonModel = imageGrpcClient.uploadMultiImage(jsonModel, products.getProductid());
        BeanUtils.copyProperties(jsonModel, products);
        productsRepository.save(products);
    }

    private String autoGenerateProductId() {
        String latestProductId = productsRepository.getLatestProductId();
        if (StringUtils.isBlank(latestProductId)) {
            return "PRODUCT00001";
        }
        int productId = Integer.parseInt(latestProductId.substring(MethodCommon.getIndexById(latestProductId)));
        if(productId >= 0 && productId < 9) {
            return "PRODUCT0000"+(productId+1);
        } else if(productId >= 9 && productId < 99) {
            return "PRODUCT000"+(productId+1);
        } else if(productId >= 99 && productId < 999) {
            return "PRODUCT00"+(productId+1);
        } else if(productId >= 999 && productId < 9999) {
            return "PRODUCT0"+(productId+1);
        } else {
            return "PRODUCT"+(productId+1);
        }
    }
}