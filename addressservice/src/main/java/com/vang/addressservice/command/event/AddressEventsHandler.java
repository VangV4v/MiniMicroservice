package com.vang.addressservice.command.event;

import com.vang.addressservice.data.Addresses;
import com.vang.addressservice.data.AddressesRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class AddressEventsHandler {

    private final AddressesRepository addressesRepository;

    @Autowired
    public AddressEventsHandler(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @EventHandler
    public void handle(AddressCreatedEvent event) {
        Addresses addresses = new Addresses();
        BeanUtils.copyProperties(event, addresses);
        addresses.setAddressid(autoGenerateId());
        addressesRepository.save(addresses);
    }

    @EventHandler
    public void handle(AddressUpdatedEvent event) {
        Addresses addresses = new Addresses();
        BeanUtils.copyProperties(event, addresses);
        addressesRepository.save(addresses);
    }

    @EventHandler
    public void handle(AddressDeletedEvent event) {
        addressesRepository.deleteById(event.getAddressid());
    }

    private String autoGenerateId() {
        String latestId = addressesRepository.getLatestId();
        int convertToInt;
        if(StringUtils.isEmpty(latestId)) {
            return "ADDRESS0001";
        }
        convertToInt = Integer.parseInt(latestId.substring(MethodCommon.getIndexById(latestId)));
        if(convertToInt >= 1 && convertToInt < 9) {
            return "ADDRESS000"+(convertToInt+1);
        } else if(convertToInt >= 9 && convertToInt < 99) {
            return "ADDRESS00"+(convertToInt+1);
        } else if(convertToInt >= 99 && convertToInt < 999) {
            return "ADDRESS0"+(convertToInt+1);
        } else {
            return "ADDRESS"+(convertToInt+1);
        }
    }
}