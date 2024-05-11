package com.vang.adminservice.command.event;

import com.google.protobuf.ByteString;
import com.vang.adminservice.command.grpc.ImageServiceClientImpl;
import com.vang.adminservice.command.model.AdminMessageModel;
import com.vang.adminservice.data.Admins;
import com.vang.adminservice.data.AdminsRepository;
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
public class AdminEventsHandler {

    private final AdminsRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ImageServiceClientImpl imageServiceClientImpl;

    @Autowired
    public AdminEventsHandler(AdminsRepository repository, KafkaTemplate<String, Object> kafkaTemplate, ImageServiceClientImpl imageServiceClientImpl) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.imageServiceClientImpl = imageServiceClientImpl;
    }

    @EventHandler
    public void handle(AdminCreatedEvent event) {

        event.setAdminid(generateAdminId() );
        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        repository.save(admins);
        AdminMessageModel messageModel = new AdminMessageModel();
        messageModel.setEmail(event.getEmail());
        messageModel.setPhone(event.getPhone());
        messageModel.setFullName(event.getFirstname() + event.getLastname());
        kafkaTemplate.send("sendmailcreateadmin", messageModel);
    }

    @EventHandler
    public void handle(AdminUpdatedEvent event) {

        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        repository.save(admins);
        if(!StringUtils.isBlank(event.getFileName())) {
            String urlImage = imageServiceClientImpl.sendUploadRequestImage(ByteString.copyFrom(event.getImage()), event.getFileName());
            admins.setAvatar(urlImage);
            if(!event.getAvatar().equals(ImageDefaultCommon.ADMIN_AVATAR_DEFAULT)) {
                imageServiceClientImpl.sendDeleteRequestImage(ImageServiceCommon.getImageKey(event.getAvatar()));
            }
        }
    }

    @EventHandler
    public void handle(AdminDeletedEvent event) {

        Admins admins = repository.findById(event.getAdminid()).get();
        repository.delete(admins);
        if(!admins.getAvatar().equals(ImageDefaultCommon.ADMIN_AVATAR_DEFAULT)) {
            imageServiceClientImpl.sendDeleteRequestImage(ImageServiceCommon.getImageKey(admins.getAvatar()));
        }
    }

    private String generateAdminId() {

        String latestAdminId = repository.getLatestId();
        int convertToInteger = Integer.parseInt(latestAdminId.substring(MethodCommon.getIndexById(latestAdminId)));
        if(convertToInteger > 0 && convertToInteger < 8) {
            return "ADMIN000"+(convertToInteger+1);
        }else if(convertToInteger >= 9 && convertToInteger < 98) {
            return "ADMIN00"+(convertToInteger+1);
        }else if(convertToInteger >= 98 && convertToInteger < 998) {
            return "ADMIN0"+(convertToInteger+1);
        }else {
            return "ADMIN"+(convertToInteger+1);
        }
    }
}