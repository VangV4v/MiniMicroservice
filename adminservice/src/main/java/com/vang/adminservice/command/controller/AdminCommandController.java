package com.vang.adminservice.command.controller;

import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import com.vang.adminservice.command.service.AdminCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vang.minimicroservice.common.ResponseCRUDCommon;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminCommandController {

    private final AdminCommandService commandService;

    @Autowired
    public AdminCommandController(AdminCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCRUDCommon> addAdmin(@RequestBody AdminRequestModel model) {

        return commandService.addAdmin(model);
    }

    @PutMapping
    public ResponseEntity<ResponseCRUDCommon> updateAdmin(@ModelAttribute UpdateAdminRequestModel model) {

        return commandService.updateAdmin(model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseCRUDCommon> deleteAdmin(@PathVariable("id") String id) {

        return commandService.deleteAdmin(id);
    }
}