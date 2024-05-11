package com.vang.adminservice.query.grpc;

import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.queries.GetAllAdmins;
import com.vang.adminservice.query.queries.GetDetailAdmin;
import com.vang.adminservice.query.service.AdminQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminQueryServiceImpl implements AdminQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public AdminQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<AdminResponseModel> getDetail(String id) {

        GetDetailAdmin detailAdmin = new GetDetailAdmin();
        detailAdmin.setAdminId(id);
        AdminResponseModel model = queryGateway.query(detailAdmin, ResponseTypes.instanceOf(AdminResponseModel.class)).join();
        if(model.isDataStatus()) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<AdminResponseModel>> getAll() {

        GetAllAdmins allAdmins = new GetAllAdmins();
        List<AdminResponseModel> listModel = queryGateway.query(allAdmins, ResponseTypes.multipleInstancesOf(AdminResponseModel.class)).join();
        return new ResponseEntity<>(listModel, HttpStatus.OK);
    }
}