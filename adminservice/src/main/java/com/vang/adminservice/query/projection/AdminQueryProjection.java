package com.vang.adminservice.query.projection;

import com.vang.adminservice.data.Admins;
import com.vang.adminservice.data.AdminsRepository;
import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.queries.GetAllAdmins;
import com.vang.adminservice.query.queries.GetDetailAdmin;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminQueryProjection {

    private final AdminsRepository repository;

    @Autowired
    public AdminQueryProjection(AdminsRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public AdminResponseModel getDetail(GetDetailAdmin detailAdmin) {

        Admins admins = repository.findById(detailAdmin.getAdminId()).orElse(new Admins());
        AdminResponseModel model = new AdminResponseModel();
        if(StringUtils.isBlank(admins.getAdminid())) {
            model.initDefaultValue();
        }else {
            BeanUtils.copyProperties(admins, model);
        }
        return model;
    }

    @QueryHandler
    public List<AdminResponseModel> getAlls(GetAllAdmins allAdmins) {

        List<Admins> listAdmins = repository.findAll();
        List<AdminResponseModel> listModel = new ArrayList<>();
        listAdmins.forEach( e -> {
            AdminResponseModel model = new AdminResponseModel();
            BeanUtils.copyProperties(e, model);
            listModel.add(model);
        });
        return listModel;
    }
}
