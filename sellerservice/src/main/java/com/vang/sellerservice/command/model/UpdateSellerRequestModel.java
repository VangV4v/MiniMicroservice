package com.vang.sellerservice.command.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class UpdateSellerRequestModel extends SellerRequestModel implements Serializable {

    @NotNull
    private String hdnOldEmail;
    @NotNull
    private String hdnOldPhone;
    @NotNull
    private String shopnameexpiration;
    private MultipartFile image;
}