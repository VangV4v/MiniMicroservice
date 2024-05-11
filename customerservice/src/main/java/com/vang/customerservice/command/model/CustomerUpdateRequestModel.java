package com.vang.customerservice.command.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class CustomerUpdateRequestModel extends CustomerRequestModel implements Serializable {
    private String hdnOldEmail;
    private String hdnOldPhone;
    private MultipartFile image;
}