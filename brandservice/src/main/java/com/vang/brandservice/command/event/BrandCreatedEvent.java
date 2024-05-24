package com.vang.brandservice.command.event;

import com.google.protobuf.ByteString;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BrandCreatedEvent {

    private Long autoAggregateIdentifier;
    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private String createddate;
    private String lastmodified;
    private int activestatus;
    private byte[] image;
    private String fileName;
}