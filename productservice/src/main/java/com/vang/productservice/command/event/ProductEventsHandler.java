package com.vang.productservice.command.event;

import com.vang.productservice.command.grpc.DeleteImageClient;
import com.vang.productservice.command.grpc.ImageGrpcClient;
import com.vang.productservice.command.grpcmodel.ProductImageJsonModel;
import com.vang.productservice.data.Products;
import com.vang.productservice.data.ProductsRepository;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.ImageServiceCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;
    private final ImageGrpcClient imageGrpcClient;
    private final DeleteImageClient deleteImageClient;

    @Autowired
    public ProductEventsHandler(ProductsRepository productsRepository, ImageGrpcClient imageGrpcClient, DeleteImageClient deleteImageClient) {
        this.productsRepository = productsRepository;
        this.imageGrpcClient = imageGrpcClient;
        this.deleteImageClient = deleteImageClient;
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

    @EventHandler
    public void handle(ProductUpdatedEvent event) {
        Products products = new Products();
        BeanUtils.copyProperties(event, products);
        productsRepository.save(products);
        ProductImageJsonModel jsonModelUpdate = new ProductImageJsonModel();
        ProductImageJsonModel jsonModelDelete = new ProductImageJsonModel();
        BeanUtils.copyProperties(event, jsonModelUpdate);
        BeanUtils.copyProperties(event, jsonModelDelete);
        jsonModelUpdate = imageGrpcClient.uploadMultiImage(jsonModelUpdate, products.getProductid());
        checkAndRemoveImageUpdate(jsonModelDelete);
        BeanUtils.copyProperties(jsonModelUpdate, products);
        productsRepository.save(products);
    }

    @EventHandler
    public void handle(ProductDeletedEvent event) {
        Products products = productsRepository.findById(event.getProductid()).get();
        productsRepository.delete(products);
        ProductImageJsonModel jsonModelDelete = new ProductImageJsonModel();
        BeanUtils.copyProperties(products, jsonModelDelete);
        checkAndRemoveImageWhenDelete(jsonModelDelete);
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

    private void checkAndRemoveImageWhenDelete(ProductImageJsonModel model) {
        if(!StringUtils.isBlank(model.getDefaultimage())) {
            String image = model.getDefaultimage();
            String urlImage = ImageServiceCommon.getImageKey(image);
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage1())) {
            String image = model.getImage1();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage2())) {
            String image = model.getImage2();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage3())) {
            String image = model.getImage3();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage4())) {
            String image = model.getImage4();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage5())) {
            String image = model.getImage5();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage6())) {
            String image = model.getImage6();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage7())) {
            String image = model.getImage7();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage8())) {
            String image = model.getImage8();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage9())) {
            String image = model.getImage9();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!StringUtils.isBlank(model.getImage10())) {
            String image = model.getImage10();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
    }

    private void checkAndRemoveImageUpdate(ProductImageJsonModel model) {
        if(!ArrayUtils.isEmpty(model.getDefaultImageByte())) {
            String image = model.getDefaultimage();
            String urlImage = ImageServiceCommon.getImageKey(image);
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage1Byte())) {
            String image = model.getImage1();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage2Byte())) {
            String image = model.getImage2();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage3Byte())) {
            String image = model.getImage3();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage4Byte())) {
            String image = model.getImage4();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage5Byte())) {
            String image = model.getImage5();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage6Byte())) {
            String image = model.getImage6();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage7Byte())) {
            String image = model.getImage7();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage8Byte())) {
            String image = model.getImage8();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage9Byte())) {
            String image = model.getImage9();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
        if(!ArrayUtils.isEmpty(model.getImage10Byte())) {
            String image = model.getImage10();
            String urlImage = image.substring(MethodCommon.getIndexById(image));
            deleteImageClient.deleteImage(urlImage);
        }
    }
}