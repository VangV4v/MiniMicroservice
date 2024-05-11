package com.vang.brandservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBrandCommand {

    @TargetAggregateIdentifier
    private Long autoAggregateIdentifier;
    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private int activestatus;
    private byte[] image;
    private String fileName;
}