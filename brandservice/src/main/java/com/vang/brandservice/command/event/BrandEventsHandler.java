package com.vang.brandservice.command.event;

import com.google.protobuf.ByteString;
import com.vang.brandservice.command.grpc.ImageServiceClientImpl;
import com.vang.brandservice.data.Brands;
import com.vang.brandservice.data.BrandsRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.ImageDefaultCommon;
import org.vang.minimicroservice.common.ImageServiceCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class BrandEventsHandler {

    private final BrandsRepository repository;

    private final ImageServiceClientImpl imageServiceClient;

    @Autowired
    public BrandEventsHandler(BrandsRepository repository, ImageServiceClientImpl imageServiceClient) {
        this.repository = repository;
        this.imageServiceClient = imageServiceClient;
    }

    @EventHandler
    @Transactional
    public void handle(BrandCreatedEvent event) {

        Brands brands = new Brands();
        BeanUtils.copyProperties(event, brands);
        brands.setBrandid(generateId());
        repository.save(brands);
        if(!StringUtils.isEmpty(event.getFileName())) {
            String urlImage = imageServiceClient.sendRequestUploadImage(ByteString.copyFrom(event.getImage()), event.getFileName());
            brands.setLogo(urlImage);
            repository.save(brands);
        }
    }

    @EventHandler
    public void handle(BrandUpdatedEvent event) {

        Brands brands = new Brands();
        BeanUtils.copyProperties(event, brands);
        repository.save(brands);
        if(!StringUtils.isEmpty(event.getFileName())) {
            String urlImage = imageServiceClient.sendRequestUploadImage(ByteString.copyFrom(event.getImage()), event.getFileName());
            brands.setLogo(urlImage);
            repository.save(brands);
            if(!ImageDefaultCommon.BRAND_LOGO_DEFAULT.equals(event.getLogo())) {
                String imageKey = ImageServiceCommon.getImageKey(event.getLogo());
                imageServiceClient.sendRequestRemoveImage(imageKey);
            }
        }

    }

    @EventHandler
    public void handle(BrandDeletedEvent event) {

        Brands brands = repository.findById(event.getBrandid()).get();
        if(!StringUtils.isBlank(brands.getLogo()) && !ImageDefaultCommon.BRAND_LOGO_DEFAULT.equals(brands.getLogo())) {

            String imageKey = ImageServiceCommon.getImageKey(brands.getLogo());
            imageServiceClient.sendRequestRemoveImage(imageKey);
        }
        repository.delete(brands);
    }

    private String generateId() {
        String latestId = repository.getByBrandIdLatest();
        String afterCut = latestId.substring(MethodCommon.getIndexById(latestId));
        int castIdToInteger = Integer.parseInt(afterCut);
        if(castIdToInteger > 0 && castIdToInteger < 9) {
            return "BRAND000"+(castIdToInteger+1);
        }else if(castIdToInteger >= 9 && castIdToInteger < 99) {
            return "BRAND00"+(castIdToInteger+1);
        }else if(castIdToInteger >= 99 && castIdToInteger < 999) {
            return "BRAND0"+(castIdToInteger+1);
        }else {
            return "BRAND"+(castIdToInteger+1);
        }
    }
}