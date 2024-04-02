package com.vang.adminservice.command.service;

import com.vang.adminservice.command.model.AdminRequestModel;
import org.springframework.http.ResponseEntity;

public interface AdminCommandService {

    public ResponseEntity<String> addAdmin(AdminRequestModel model);

    public ResponseEntity<String> updateAdmin(AdminRequestModel model);

    public ResponseEntity<String> deleteAdmin(String id);
}