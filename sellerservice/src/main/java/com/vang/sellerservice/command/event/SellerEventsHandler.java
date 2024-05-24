package com.vang.sellerservice.command.event;

import com.google.protobuf.ByteString;
import com.vang.sellerservice.command.grpc.ImageServiceClientImpl;
import com.vang.sellerservice.command.model.SellerMessageModel;
import com.vang.sellerservice.data.Sellers;
import com.vang.sellerservice.data.SellersRepository;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.common.ImageDefaultCommon;
import org.vang.minimicroservice.common.ImageServiceCommon;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class SellerEventsHandler {

    private final SellersRepository sellersRepository;
    private final ImageServiceClientImpl imageServiceClient;
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public SellerEventsHandler(SellersRepository sellersRepository, KafkaTemplate<String, Object> kafkaTemplate, ImageServiceClientImpl imageServiceClient) {
        this.sellersRepository = sellersRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.imageServiceClient = imageServiceClient;
    }

    @EventHandler
    public void handle(SellerCreatedEvent event) {

        event.setSellerid(autoGenerateId());
        Sellers sellers = new Sellers();
        BeanUtils.copyProperties(event, sellers);
        sellersRepository.save(sellers);
        sendNotification(event);
    }

    @EventHandler
    public void handle(SellerUpdatedEvent event) {
        Sellers sellers = new Sellers();
        BeanUtils.copyProperties(event, sellers);
        sellersRepository.save(sellers);
        if(!ArrayUtils.isEmpty(event.getImage())) {
            String urlImage = imageServiceClient.sendRequestUploadImage(ByteString.copyFrom(event.getImage()), event.getFileName());
            sellers.setAvatar(urlImage);
            sellersRepository.save(sellers);
            checkAndRemoveImage(event.getAvatar());
        }
    }

    @EventHandler
    public void handle(SellerDeletedEvent event) {

        Sellers sellers = sellersRepository.findById(event.getSellerid()).get();
        sellersRepository.delete(sellers);
        checkAndRemoveImage(sellers.getAvatar());
    }

    private void sendNotification(SellerCreatedEvent event) {
        SellerMessageModel messageModel = new SellerMessageModel();
        messageModel.setEmail(event.getEmail());
        messageModel.setUsername(event.getUsername());
        kafkaTemplate.send("registrationSeller", messageModel);
    }

    private void checkAndRemoveImage(String image) {
        if(!image.equals(ImageDefaultCommon.SELLER_AVATAR_DEFAULT)) {
            System.out.println("----------------> "+ImageServiceCommon.getImageKey(image));
            imageServiceClient.sendRequestRemoveImage(ImageServiceCommon.getImageKey(image));
        }
    }

    private String autoGenerateId() {
        String latestId = sellersRepository.getLastestId();
        int convertToInt = 0;
        if(StringUtils.isBlank(latestId)) {
            return "SELLER0001";
        }
        convertToInt = Integer.parseInt(latestId.substring(MethodCommon.getIndexById(latestId)));
        if(convertToInt > 0 && convertToInt < 9) {
            return "SELLER000"+(convertToInt + 1);
        }else if(convertToInt > 9 && convertToInt < 99) {
            return "SELLER00"+(convertToInt + 1);
        }else if(convertToInt > 99 && convertToInt < 999) {
            return "SELLER0"+(convertToInt + 1);
        }else {
            return "SELLER"+(convertToInt + 1);
        }
    }
}