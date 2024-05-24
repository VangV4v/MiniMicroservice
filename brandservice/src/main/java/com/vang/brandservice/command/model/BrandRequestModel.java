package com.vang.brandservice.command.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class BrandRequestModel implements Serializable {

    private String brandid;
    private String brandname;
    private String description;
    private String logo;
    private String createddate;
    private String lastmodified;
    private MultipartFile image;
    private int activestatus;
}