package com.vang.brandservice.command.event;

import com.vang.brandservice.data.Brands;
import com.vang.brandservice.data.BrandsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandEventsHandler {

    @Autowired
    private BrandsRepository repository;

    @EventHandler
    public void handle(BrandCreatedEvent event) {

        Brands brands = new Brands();
        BeanUtils.copyProperties(event, brands);
        repository.save(brands);
    }

    @EventHandler
    public void handle(BrandUpdatedEvent event) {

        Brands brands = new Brands();
        BeanUtils.copyProperties(event, brands);
        repository.save(brands);
    }

    @EventHandler
    public void handle(BrandDeletedEvent event) {

        repository.deleteById(event.getBrandid());
    }

}