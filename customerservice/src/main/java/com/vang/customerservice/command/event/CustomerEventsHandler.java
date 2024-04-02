package com.vang.customerservice.command.event;

import com.vang.customerservice.command.model.CustomerMessageModel;
import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventsHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final CustomersRepository repository;

    @Autowired
    public CustomerEventsHandler(KafkaTemplate<String, Object> kafkaTemplate, CustomersRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    @EventHandler
    public void handle(CustomerCreatedEvent event) {

        Customers customers = new Customers();
        BeanUtils.copyProperties(event, customers);
        customers.setCustomerid(repository.autoGenerateId());
        repository.save(customers);
        CustomerMessageModel message = new CustomerMessageModel();
        message.setEmail(customers.getEmail());
        message.setUsername(customers.getUsername());
        message.setFullName(customers.getFirstname()+" "+customers.getLastname());
        message.setPhone(customers.getPhone());
        kafkaTemplate.send("sendmailcreatecustomer", message);
    }

    @EventHandler
    public void handle(CustomerUpdatedEvent event) {

        Customers customers = new Customers();
        BeanUtils.copyProperties(event, customers);
        repository.save(customers);
    }

    @EventHandler
    public void handle(CustomerDeletedEvent event) {

        repository.deleteById(event.getCustomerid());
    }

}