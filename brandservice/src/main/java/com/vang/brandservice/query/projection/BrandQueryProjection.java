package com.vang.brandservice.query.projection;

import com.vang.brandservice.data.Brands;
import com.vang.brandservice.data.BrandsRepository;
import com.vang.brandservice.query.model.BrandResponseModel;
import com.vang.brandservice.query.queries.GetAllBrands;
import com.vang.brandservice.query.queries.GetDetailBrand;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandQueryProjection {

    private final BrandsRepository repository;

    @Autowired
    public BrandQueryProjection(BrandsRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public BrandResponseModel getDetail(GetDetailBrand detailBrand) {

        Brands brands = repository.findById(detailBrand.getId()).orElse(new Brands());
        BrandResponseModel model = new BrandResponseModel();
        if(StringUtils.isBlank(brands.getBrandid())) {
            model.initializeValue();
        }else {
            BeanUtils.copyProperties(brands, model);
        }
        return model;
    }

    @QueryHandler
    public List<BrandResponseModel> getAll(GetAllBrands allBrands) {

        List<Brands> listBrands = repository.findAll();
        List<BrandResponseModel> listModel = new ArrayList<>();
        listBrands.forEach(e -> {
            BrandResponseModel model = new BrandResponseModel();
            BeanUtils.copyProperties(e, model);
            listModel.add(model);
        });
        return listModel;
    }

}