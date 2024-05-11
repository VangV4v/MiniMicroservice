package com.vang.adminservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAdminRequestModel extends AdminRequestModel implements Serializable {
    private String hdnOldEmail;
    private String hdnOldPhone;
    private MultipartFile image;
}