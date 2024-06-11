package com.vang.confirmorderservice.service.impl;

import com.google.gson.Gson;
import com.vang.confirmorderservice.data.Orders;
import com.vang.confirmorderservice.data.OrdersRepository;
import com.vang.confirmorderservice.grpc.GetAuthSellerInformationClientImpl;
import com.vang.confirmorderservice.grpc.GetSellerByUsernameClientImpl;
import com.vang.confirmorderservice.grpcmodel.SellerJsonModel;
import com.vang.confirmorderservice.model.OrderResponseModel;
import com.vang.confirmorderservice.service.ConfirmOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.MessageCommon;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ConfirmOrderServiceImpl implements ConfirmOrderService {

    private final GetAuthSellerInformationClientImpl getAuthSellerInformationClient;
    private final GetSellerByUsernameClientImpl getSellerByUsernameClient;
    private final OrdersRepository ordersRepository;
    private final Gson gson = new Gson();

    @Autowired
    public ConfirmOrderServiceImpl(GetAuthSellerInformationClientImpl getAuthSellerInformationClient, GetSellerByUsernameClientImpl getSellerByUsernameClient, OrdersRepository ordersRepository) {
        this.getAuthSellerInformationClient = getAuthSellerInformationClient;
        this.getSellerByUsernameClient = getSellerByUsernameClient;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public ResponseEntity<Object> getOrders() {

        String getAuthSeller = getAuthSellerInformationClient.getAuthSellerInformation();
        String sellerResponse;
        SellerJsonModel sellerJsonModel;
        if(StringUtils.isEmpty(getAuthSeller)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        sellerResponse = getSellerByUsernameClient.getSellerByUsername(getAuthSeller);
        sellerJsonModel = gson.fromJson(sellerResponse, SellerJsonModel.class);
        List<Orders> listOrders = ordersRepository.findAllBySellerIdAndStatus(sellerJsonModel.getSellerid());
        List<OrderResponseModel> listModels = new ArrayList<>();
        listOrders.forEach(e -> {
            OrderResponseModel model = new OrderResponseModel();
            BeanUtils.copyProperties(e, model);
            listModels.add(model);
        });
        return new ResponseEntity<>(listModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getDetail(String orderId) {

        String sellerResponse;
        SellerJsonModel sellerJsonModel;
        Orders orders;
        String getAuthSeller = getAuthSellerInformationClient.getAuthSellerInformation();
        if(StringUtils.isEmpty(getAuthSeller)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        sellerResponse = getSellerByUsernameClient.getSellerByUsername(getAuthSeller);
        sellerJsonModel = gson.fromJson(sellerResponse, SellerJsonModel.class);
        orders = ordersRepository.findByIdAndSellerId(orderId, sellerJsonModel.getSellerid());
        if(ObjectUtils.isEmpty(orders)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(Set.of(MessageCommon.NO_RESULT)).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        OrderResponseModel responseModel = new OrderResponseModel();
        BeanUtils.copyProperties(orders, responseModel);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAlls() {
        String sellerResponse;
        SellerJsonModel sellerJsonModel;
        Orders orders;
        String getAuthSeller = getAuthSellerInformationClient.getAuthSellerInformation();
        if(StringUtils.isEmpty(getAuthSeller)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        sellerResponse = getSellerByUsernameClient.getSellerByUsername(getAuthSeller);
        sellerJsonModel = gson.fromJson(sellerResponse, SellerJsonModel.class);
        List<Orders> listOrders = ordersRepository.findAllBySellerId(sellerJsonModel.getSellerid());
        List<OrderResponseModel> listModels = new ArrayList<>();
        listOrders.forEach(e -> {
            OrderResponseModel model = new OrderResponseModel();
            BeanUtils.copyProperties(e, model);
            listModels.add(model);
        });
        return new ResponseEntity<>(listModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> approveOrder(String orderId) {
        String sellerResponse;
        SellerJsonModel sellerJsonModel;
        Orders orders;
        String getAuthSeller = getAuthSellerInformationClient.getAuthSellerInformation();
        if(StringUtils.isEmpty(getAuthSeller)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        sellerResponse = getSellerByUsernameClient.getSellerByUsername(getAuthSeller);
        sellerJsonModel = gson.fromJson(sellerResponse, SellerJsonModel.class);
        orders = ordersRepository.findByIdAndSellerId(orderId, sellerJsonModel.getSellerid());
        orders.setConfirmstatus(NumberUtils.ONE);
        ordersRepository.save(orders);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message("Success").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> denyOrder(String orderId) {

        String getAuthSeller = getAuthSellerInformationClient.getAuthSellerInformation();
        if(StringUtils.isEmpty(getAuthSeller)) {
            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        ordersRepository.deleteById(orderId);
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(false).message("Success").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}