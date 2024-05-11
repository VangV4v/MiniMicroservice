package com.vang.customerservice.command.event;

import com.google.protobuf.ByteString;
import com.vang.customerservice.command.grpc.ImageServiceClientImpl;
import com.vang.customerservice.command.model.NotifyRegistrationAccountMessage;
import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
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
public class CustomerEventsHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final CustomersRepository repository;

    private final ImageServiceClientImpl imageServiceClient;

    @Autowired
    public CustomerEventsHandler(KafkaTemplate<String, Object> kafkaTemplate, CustomersRepository repository, ImageServiceClientImpl imageServiceClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
        this.imageServiceClient = imageServiceClient;
    }

    @EventHandler
    public void handle(CustomerCreatedEvent event) {

            Customers customers = new Customers();
            BeanUtils.copyProperties(event, customers);
            customers.setCustomerid(generateId());
            repository.save(customers);
            sendNotifycation(event.getEmail());
    }

    @EventHandler
    public void handle(CustomerUpdatedEvent event) {

        Customers customers = new Customers();
        BeanUtils.copyProperties(event, customers);
        repository.save(customers);
        if(!StringUtils.isBlank(event.getFileName())) {
            String urlImage = imageServiceClient.sendRequestUploadImage(ByteString.copyFrom(event.getImage()), event.getFileName());
            customers.setAvatar(urlImage);
            repository.save(customers);
            if(!ImageDefaultCommon.CUSTOMER_AVATAR_DEFAULT.equals(event.getAvatar()) && !StringUtils.isBlank(event.getAvatar())) {
                imageServiceClient.sendRequestRemoveImage(ImageServiceCommon.getImageKey(event.getAvatar()));
            }
        }
    }

    @EventHandler
    public void handle(CustomerDeletedEvent event) {

        repository.deleteById(event.getCustomerid());
    }

    private void sendNotifycation(String email) {
        NotifyRegistrationAccountMessage message = new NotifyRegistrationAccountMessage();
        message.setEmail(email);
        kafkaTemplate.send("notifyRegistrationCustomer" ,message);
    }

    private String generateId() {
        String latestId = repository.getLatestCustomerId();
        String afterCutEnd = latestId.substring(MethodCommon.getIndexById(latestId));
        int toNumber = Integer.parseInt(afterCutEnd);
        if(toNumber == 0) {
            return "CUSTOMER0001";
        }
        if(toNumber > 0 && toNumber < 9) {
            return "CUSTOMER000"+(toNumber+1);
        }else if(toNumber >= 9 && toNumber < 99) {
            return "CUSTOMER00"+(toNumber+1);
        }else if(toNumber >= 99 && toNumber < 999){
            return "CUSTOMER0"+(toNumber + 1);
        }else {
            return "CUSTOMER"+(toNumber+1);
        }
    }

}