package com.vang.adminservice.command.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UpdateAdminRequestModel extends AdminRequestModel implements Serializable {
    private String hdnOldEmail;
    private String hdnOldPhone;
    private MultipartFile image;
}