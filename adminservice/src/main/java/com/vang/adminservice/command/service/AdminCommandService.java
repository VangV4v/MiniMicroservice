package com.vang.adminservice.command.service;

import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import org.springframework.http.ResponseEntity;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

public interface AdminCommandService {

    public ResponseEntity<ResponseCRUDCommon> addAdmin(AdminRequestModel model);

    public ResponseEntity<ResponseCRUDCommon> updateAdmin(UpdateAdminRequestModel model);

    public ResponseEntity<ResponseCRUDCommon> deleteAdmin(String id);
}