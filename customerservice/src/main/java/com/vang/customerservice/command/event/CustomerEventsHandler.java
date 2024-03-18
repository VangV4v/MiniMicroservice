package com.vang.customerservice.command.event;

import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventsHandler {

    @Autowired
    private CustomersRepository repository;

    @EventHandler
    public void handle(CustomerCreatedEvent event) {

        Customers customers = new Customers();
        BeanUtils.copyProperties(event, customers);
        customers.setCustomerid(repository.autoGenerateId());
        repository.save(customers);
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