package com.vang.categoryservice.command.aggregate;

import com.vang.categoryservice.command.command.CreateCategoryCommand;
import com.vang.categoryservice.command.command.DeleteCategoryCommand;
import com.vang.categoryservice.command.command.UpdateCategoryCommand;
import com.vang.categoryservice.command.event.CategoryCreatedEvent;
import com.vang.categoryservice.command.event.CategoryDeletedEvent;
import com.vang.categoryservice.command.event.CategoryUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class CategoryAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String categoryid;
    private String categoryname;
    private String description;
    private int activestatus;
    private String createddate;
    private String lastmodified;

    public CategoryAggregate() {}

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        CategoryCreatedEvent event = new CategoryCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CategoryAggregate(UpdateCategoryCommand command) {
        CategoryUpdatedEvent event = new CategoryUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CategoryAggregate(DeleteCategoryCommand command) {
        CategoryDeletedEvent event = new CategoryDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(CategoryCreatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.categoryid = event.getCategoryid();
        this.categoryname = event.getCategoryname();
        this.description = event.getDescription();
        this.activestatus = event.getActivestatus();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
    }

    @EventSourcingHandler
    public void handle(CategoryUpdatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.categoryid = event.getCategoryid();
        this.categoryname = event.getCategoryname();
        this.description = event.getDescription();
        this.activestatus = event.getActivestatus();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
    }

    @EventSourcingHandler
    public void handle(CategoryDeletedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.categoryid = event.getCategoryid();
    }
}