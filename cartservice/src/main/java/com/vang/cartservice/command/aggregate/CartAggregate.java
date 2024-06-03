package com.vang.cartservice.command.aggregate;

import com.vang.cartservice.command.command.CreateCartCommand;
import com.vang.cartservice.command.command.DeleteAllCartCommand;
import com.vang.cartservice.command.command.DeleteCartCommand;
import com.vang.cartservice.command.command.UpdateCartCommand;
import com.vang.cartservice.command.event.AllCartsDeletedEvent;
import com.vang.cartservice.command.event.CartCreatedEvent;
import com.vang.cartservice.command.event.CartDeletedEvent;
import com.vang.cartservice.command.event.CartUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class CartAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String cartid;
    private String customerid;
    private String productid;
    private String productdetail;
    private int quantity;

    public CartAggregate() {}

    @CommandHandler
    public CartAggregate(CreateCartCommand command) {
        CartCreatedEvent event = new CartCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CartAggregate(UpdateCartCommand command) {
        CartUpdatedEvent event = new CartUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CartAggregate(DeleteCartCommand command) {
        CartDeletedEvent event = new CartDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CartAggregate(DeleteAllCartCommand command) {
        AllCartsDeletedEvent event = new AllCartsDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(CartCreatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.cartid = event.getCartid();
        this.customerid = event.getCustomerid();
        this.productid = event.getProductid();
        this.productdetail = event.getProductdetail();
        this.quantity = event.getQuantity();
    }

    @EventSourcingHandler
    public void handle(CartUpdatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.cartid = event.getCartid();
        this.customerid = event.getCustomerid();
        this.productid = event.getProductid();
        this.productdetail = event.getProductdetail();
        this.quantity = event.getQuantity();
    }

    @EventSourcingHandler
    public void handle(CartDeletedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.cartid = event.getCartid();
    }

    @EventSourcingHandler
    public void handle(AllCartsDeletedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.customerid = event.getCustomerid();
    }
}