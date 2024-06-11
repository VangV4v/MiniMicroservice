package com.vang.orderservice.configuation;

import com.vang.orderservice.gen.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vang.minimicroservice.common.GrpcCommon;

@Configuration
public class GrpcConfiguation {

    @Bean
    public GetCartByIdGrpc.GetCartByIdBlockingStub getCartByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.CART_SERVER_PORT).usePlaintext().build();
        return GetCartByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.AUTH_CUSTOMER_SERVER_PORT).usePlaintext().build();
        return GetAuthCustomerInfoGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetCustomerByUsernameGrpc.GetCustomerByUsernameBlockingStub getCustomerByUsernameBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.CUSTOMER_SERVER_PORT).usePlaintext().build();
        return GetCustomerByUsernameGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetProductByIdGrpc.GetProductByIdBlockingStub getProductByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.PRODUCT_SERVER_PORT).usePlaintext().build();
        return GetProductByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetAddressByIdGrpc.GetAddressByIdBlockingStub getAddressByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.ADDRESS_SERVER_PORT).usePlaintext().build();
        return GetAddressByIdGrpc.newBlockingStub(channel);
    }

}