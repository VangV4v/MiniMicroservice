package com.vang.orderservice.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vang.orderservice.data.Orders;
import com.vang.orderservice.data.OrdersRepository;
import com.vang.orderservice.grpc.*;
import com.vang.orderservice.grpcmodel.AddressJsonModel;
import com.vang.orderservice.grpcmodel.CartJsonModel;
import com.vang.orderservice.grpcmodel.CustomerJsonModel;
import com.vang.orderservice.grpcmodel.ProductJsonModel;
import com.vang.orderservice.model.CreateOrderRequestModel;
import com.vang.orderservice.model.OrderRequestModel;
import com.vang.orderservice.model.OrderResponseModel;
import com.vang.orderservice.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.DateCommon;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.ResponseCRUDCommon;
import org.vang.minimicroservice.method.MethodCommon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final GetCartByIdClientImpl getCartById;
    private final GetAuthCustomerClientImpl getAuthCustomerClient;
    private final GetCustomerByUsernameClientImpl getCustomerByUsernameClient;
    private final GetProductByIdClientImpl getProductByIdClient;
    private final OrdersRepository ordersRepository;
    private final GetAddressByIdClientImpl getAddressByIdClient;
    private final DeleteCartByIdClientImpl deleteCartByIdClient;
    private final Gson gson = new Gson();

    @Autowired
    public OrderServiceImpl(GetCartByIdClientImpl getCartByIdClient, GetAuthCustomerClientImpl getAuthCustomerClient, GetCustomerByUsernameClientImpl getCustomerByUsernameClient, GetProductByIdClientImpl getProductByIdClient, OrdersRepository ordersRepository, GetAddressByIdClientImpl getAddressByIdClient, DeleteCartByIdClientImpl deleteCartByIdClient) {
        this.getCartById = getCartByIdClient;
        this.getAuthCustomerClient = getAuthCustomerClient;
        this.getCustomerByUsernameClient = getCustomerByUsernameClient;
        this.getProductByIdClient = getProductByIdClient;
        this.ordersRepository = ordersRepository;
        this.getAddressByIdClient = getAddressByIdClient;
        this.deleteCartByIdClient = deleteCartByIdClient;
    }

    @Override
    public ResponseEntity<ResponseCRUDCommon> createOrder(CreateOrderRequestModel model) {

        String username = getAuthCustomerClient.getAuthCustomerInformation();
        String addressDetail = getAddressByIdClient.getAddressByIdJson(model.getAddressId());
        AddressJsonModel addressJsonModel = gson.fromJson(addressDetail, AddressJsonModel.class);
        String responseCustomer;
        CustomerJsonModel customerJsonModel;
        if(StringUtils.isEmpty(username)) {

            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        responseCustomer = getCustomerByUsernameClient.getCustomerByUsername(username);
        customerJsonModel = gson.fromJson(responseCustomer, CustomerJsonModel.class);
        if(!StringUtils.isEmpty(model.getCartId())) {

            saveData(model.getCartId(), username, customerJsonModel.getCustomerid(), responseCustomer, addressJsonModel, model.getNote());
            deleteCartByIdClient.deleteByCartId(model.getCartId());
        } else if(!ObjectUtils.isEmpty(model.getListCartId()) || !model.getListCartId().isEmpty()) {

            String response = getCartById.getListCartData(model.getListCartId());
            List<CartJsonModel> listJsonModels = gson.fromJson(response, new TypeToken<List<CartJsonModel>>(){}.getType());
            listJsonModels.forEach(e -> {
                saveData(e.getCartid(), username, customerJsonModel.getCustomerid(), responseCustomer, addressJsonModel, model.getNote());
                deleteCartByIdClient.deleteByCartId(e.getCartid());
            });
        }
        ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllOrders() {

        String username = getAuthCustomerClient.getAuthCustomerInformation();
        String responseCustomer;
        CustomerJsonModel customerJsonModel;
        if(StringUtils.isEmpty(username)) {

            ResponseCRUDCommon response = ResponseCRUDCommon.builder().errorStatus(true).errorMessage(MethodCommon.unauthorized()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        responseCustomer = getCustomerByUsernameClient.getCustomerByUsername(username);
        customerJsonModel = gson.fromJson(responseCustomer, CustomerJsonModel.class);
        List<Orders> listOrders = ordersRepository.findAllByCustomerId(customerJsonModel.getCustomerid());
        List<OrderResponseModel> listModels = new ArrayList<>();
        listOrders.forEach(e -> {
            OrderResponseModel model = new OrderResponseModel();
            BeanUtils.copyProperties(e, model);
            listModels.add(model);
        });
        return new ResponseEntity<>(listModels, HttpStatus.OK);
    }

    private void saveData(String cartId, String username, String customerId, String customerDetail, AddressJsonModel addressJsonModel, String note) {

        String response = getCartById.getCartData(cartId);
        CartJsonModel cartJsonModel = gson.fromJson(response, CartJsonModel.class);
        String productResponse = getProductByIdClient.getProductById(cartJsonModel.getProductid());
        ProductJsonModel productJsonModel = gson.fromJson(productResponse, ProductJsonModel.class);
        OrderRequestModel orderRequestModel = new OrderRequestModel();
        BigDecimal totalPrice = BigDecimal.valueOf(cartJsonModel.getQuantity() * productJsonModel.getPrice().doubleValue());
        orderRequestModel.setCustomerid(customerId);
        orderRequestModel.setCustomerdetail(customerDetail);
        orderRequestModel.setProductid(productJsonModel.getProductid());
        orderRequestModel.setAddressid(addressJsonModel.getAddressid());
        orderRequestModel.setAddressdetail(addressJsonModel.getAddressdetail());
        orderRequestModel.setProductdetail(productResponse);
        orderRequestModel.setSellerid(productJsonModel.getSellerid());
        orderRequestModel.setNote(note);
        orderRequestModel.setOrderdate(DateCommon.getDateTimeCurrent());
        orderRequestModel.setQuantity(cartJsonModel.getQuantity());
        orderRequestModel.setPrice(productJsonModel.getPrice());
        orderRequestModel.setTotalprice(totalPrice);
        orderRequestModel.setConfirmstatus(NumberUtils.ZERO);
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderRequestModel, orders);
        ordersRepository.save(orders);
    }

}