package com.vang.cartservice.query.projection;

import com.google.gson.Gson;
import com.vang.cartservice.command.grpcmodel.ProductJsonModel;
import com.vang.cartservice.data.Carts;
import com.vang.cartservice.data.CartsRepository;
import com.vang.cartservice.query.model.CartResponseModel;
import com.vang.cartservice.query.query.GetByCustomerId;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartQueryProjection {

    private final CartsRepository cartsRepository;

    public CartQueryProjection(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @QueryHandler
    public List<CartResponseModel> getAllByCustomerId(GetByCustomerId getByCustomerId) {

        List<Carts> listCarts = cartsRepository.findAllByCustomerId(getByCustomerId.getCustomerId());
        List<CartResponseModel> listModel = new ArrayList<>();
        Gson gson = new Gson();
        listCarts.forEach(e -> {
            CartResponseModel model = new CartResponseModel();
            BeanUtils.copyProperties(e, model);
            ProductJsonModel jsonModel = gson.fromJson(e.getProductdetail(), ProductJsonModel.class);
            model.setProductdetail(jsonModel);
            listModel.add(model);
        });
        return listModel;
    }
}