package com.vang.productservice.command.aggregate;

import com.vang.productservice.command.command.CreateProductCommand;
import com.vang.productservice.command.command.DeleteProductCommand;
import com.vang.productservice.command.command.UpdateProductCommand;
import com.vang.productservice.command.event.ProductCreatedEvent;
import com.vang.productservice.command.event.ProductDeletedEvent;
import com.vang.productservice.command.event.ProductUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@Data
public class ProductAggregate {

    @AggregateIdentifier
    private Long autoAggregateIdentifier;
    private String productid;
    private String brandid;
    private String branddetail;
    private String categoryid;
    private String categorydetail;
    private String sellerid;
    private String sellerdetail;
    private String productname;
    private String description;
    private BigDecimal price;
    private int quantity;
    private int soldquantity;
    private String createddate;
    private String lastmodified;
    private String defaultimage;
    private byte[] defaultImageByte;
    private String image1;
    private byte[] image1Byte;
    private String image2;
    private byte[] image2Byte;
    private String image3;
    private byte[] image3Byte;
    private String image4;
    private byte[] image4Byte;
    private String image5;
    private byte[] image5Byte;
    private String image6;
    private byte[] image6Byte;
    private String image7;
    private byte[] image7Byte;
    private String image8;
    private byte[] image8Byte;
    private String image9;
    private byte[] image9Byte;
    private String image10;
    private byte[] image10Byte;
    private int activestatus;

    public ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        ProductCreatedEvent event = new ProductCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public ProductAggregate(UpdateProductCommand command) {
        ProductUpdatedEvent event = new ProductUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public ProductAggregate(DeleteProductCommand command) {
        ProductDeletedEvent event = new ProductDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(ProductCreatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.productid = event.getProductid();
        this.brandid = event.getBrandid();
        this.branddetail = event.getBranddetail();
        this.categoryid = event.getCategoryid();
        this.categorydetail = event.getCategorydetail();
        this.sellerid = event.getSellerid();
        this.sellerdetail = event.getSellerdetail();
        this.productname = event.getProductname();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.soldquantity = event.getSoldquantity();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
        this.defaultimage = event.getDefaultimage();
        this.image1 = event.getImage1();
        this.image1Byte = event.getImage1Byte();
        this.image2 = event.getImage2();
        this.image2Byte = event.getImage2Byte();
        this.image3 = event.getImage3();
        this.image4 = event.getImage4();
        this.image5 = event.getImage5();
        this.image6 = event.getImage6();
        this.image7 = event.getImage7();
        this.image8 = event.getImage8();
        this.image9 = event.getImage9();
        this.image10 = event.getImage10();
        this.activestatus = event.getActivestatus();
    }

    @EventSourcingHandler
    public void handle(ProductUpdatedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.productid = event.getProductid();
        this.brandid = event.getBrandid();
        this.branddetail = event.getBranddetail();
        this.categoryid = event.getCategoryid();
        this.categorydetail = event.getCategorydetail();
        this.sellerid = event.getSellerid();
        this.sellerdetail = event.getSellerdetail();
        this.productname = event.getProductname();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.soldquantity = event.getSoldquantity();
        this.createddate = event.getCreateddate();
        this.lastmodified = event.getLastmodified();
        this.defaultimage = event.getDefaultimage();
        this.image1 = event.getImage1();
        this.image1Byte = event.getImage1Byte();
        this.image2 = event.getImage2();
        this.image2Byte = event.getImage2Byte();
        this.image3 = event.getImage3();
        this.image4 = event.getImage4();
        this.image5 = event.getImage5();
        this.image6 = event.getImage6();
        this.image7 = event.getImage7();
        this.image8 = event.getImage8();
        this.image9 = event.getImage9();
        this.image10 = event.getImage10();
        this.activestatus = event.getActivestatus();
    }

    @EventSourcingHandler
    public void handle(ProductDeletedEvent event) {
        this.autoAggregateIdentifier = event.getAutoAggregateIdentifier();
        this.productid = event.getProductid();
    }
}
