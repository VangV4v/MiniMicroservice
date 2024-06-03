package com.vang.cartservice.command.event;

import com.vang.cartservice.data.Carts;
import com.vang.cartservice.data.CartsRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class CartEventsHandler {

    private final CartsRepository cartsRepository;

    @Autowired
    public CartEventsHandler(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @EventHandler
    public void handle(CartCreatedEvent event) {
        Carts carts = new Carts();
        BeanUtils.copyProperties(event, carts);
        carts.setCartid(autoGenerateId());
        cartsRepository.save(carts);
    }

    @EventHandler
    public void handle(CartUpdatedEvent event) {
        Carts carts = new Carts();
        BeanUtils.copyProperties(event, carts);
        cartsRepository.save(carts);
    }

    @EventHandler
    public void handle(CartDeletedEvent event) {
        cartsRepository.deleteById(event.getCartid());
    }

    @EventHandler
    public void handle(AllCartsDeletedEvent event) {
        cartsRepository.deleteAllByCustomerId(event.getCustomerid());
    }

    private String autoGenerateId() {
        String latestId = cartsRepository.getLatestId();
        int convertToInt = 0;
        if(StringUtils.isBlank(latestId)) {
            return "CART000001";
        }
        convertToInt = Integer.parseInt(latestId.substring(MethodCommon.getIndexById(latestId)));
        if(convertToInt >= 1 && convertToInt < 9) {
            return "CART00000"+(convertToInt+1);
        } else if(convertToInt >= 9 && convertToInt < 99) {
            return "CART0000"+(convertToInt+1);
        } else if(convertToInt >= 99 && convertToInt < 999) {
            return "CART000"+(convertToInt+1);
        } else if(convertToInt >= 999 && convertToInt < 9999) {
            return "CART00"+(convertToInt+1);
        } else if(convertToInt >= 9999 && convertToInt < 99999) {
            return "CART0"+(convertToInt+1);
        } else {
            return "CART"+(convertToInt+1);
        }
    }
}