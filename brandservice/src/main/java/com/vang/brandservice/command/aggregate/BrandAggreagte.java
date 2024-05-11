package com.vang.brandservice.command.aggregate;

import com.google.protobuf.ByteString;
import com.vang.brandservice.command.command.CreateBrandCommand;
import com.vang.brandservice.command.command.DeleteBrandCommand;
import com.vang.brandservice.command.command.UpdateBrandCommand;
import com.vang.brandservice.command.event.BrandCreatedEvent;
import com.vang.brandservice.command.event.BrandDeletedEvent;
import com.vang.brandservice.command.event.BrandUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

@Data
@Aggregate
public class BrandAggreagte {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private int activestatus;
    private byte[] image;
    private String fileName;

    public BrandAggreagte() {}

    @CommandHandler
    public BrandAggreagte(CreateBrandCommand command) {

        BrandCreatedEvent event = new BrandCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public BrandAggreagte(UpdateBrandCommand command) {

        BrandUpdatedEvent event = new BrandUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public BrandAggreagte(DeleteBrandCommand command) {

        BrandDeletedEvent event = new BrandDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(BrandCreatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.brandname = event.getBrandname();
        this.description = event.getDescription();
        this.logo = event.getLogo();
        this.activestatus = event.getActivestatus();
        this.image = event.getImage();
        this.fileName = event.getFileName();
    }

    @EventSourcingHandler
    public void handle(BrandUpdatedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.brandid = event.getBrandid();
        this.brandname = event.getBrandname();
        this.description = event.getDescription();
        this.logo = event.getLogo();
        this.activestatus = event.getActivestatus();
        this.image = event.getImage();
        this.fileName = event.getFileName();
    }

    @EventSourcingHandler
    public void handle(BrandDeletedEvent event) {

        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.brandid = event.getBrandid();
    }

}