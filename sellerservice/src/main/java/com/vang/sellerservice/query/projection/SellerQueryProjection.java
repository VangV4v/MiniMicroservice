package com.vang.sellerservice.query.projection;

import com.vang.sellerservice.data.Sellers;
import com.vang.sellerservice.data.SellersRepository;
import com.vang.sellerservice.query.model.SellerResponseModel;
import com.vang.sellerservice.query.queries.GetAllSellers;
import com.vang.sellerservice.query.queries.GetBySellerId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellerQueryProjection {

    private final SellersRepository sellersRepository;

    @Autowired
    public SellerQueryProjection(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @QueryHandler
    public SellerResponseModel getBySellerId(GetBySellerId bySellerId) {

        Sellers sellers = sellersRepository.findById(bySellerId.getSellerId()).orElse(new Sellers());
        SellerResponseModel sellerResponseModel = new SellerResponseModel();
        if(StringUtils.isBlank(sellers.getSellerid())) {
            sellerResponseModel.initialize();
        }else {
            BeanUtils.copyProperties(sellers, sellerResponseModel);
        }
        return sellerResponseModel;
    }

    @QueryHandler
    public List<SellerResponseModel> getAllSellers(GetAllSellers allSellers) {

        List<Sellers> sellers = sellersRepository.findAll();
        List<SellerResponseModel> sellerResponseModels = new ArrayList<SellerResponseModel>();
        sellers.forEach(e -> {
            SellerResponseModel sellerResponseModel = new SellerResponseModel();
            BeanUtils.copyProperties(e, sellerResponseModel);
            sellerResponseModels.add(sellerResponseModel);
        });
        return sellerResponseModels;
    }
}