package com.vang.sellerservice.command.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerDeletedEvent {

    private Long autoAggregateIdentifier;
    private String sellerid;
}