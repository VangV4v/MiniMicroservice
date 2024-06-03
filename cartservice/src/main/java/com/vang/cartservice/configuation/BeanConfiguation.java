package com.vang.cartservice.configuation;

import com.vang.cartservice.command.grpc.gen.GetAuthCustomerInfoGrpc;
import com.vang.cartservice.command.grpc.gen.GetCustomerIDGrpc;
import com.vang.cartservice.command.grpc.gen.GetProductByIdGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vang.minimicroservice.common.GrpcCommon;

@Configuration
public class BeanConfiguation {

    @Bean
    public GetAuthCustomerInfoGrpc.GetAuthCustomerInfoBlockingStub getAuthCustomerInfoBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.AUTH_CUSTOMER_SERVER_PORT).usePlaintext().build();
        return GetAuthCustomerInfoGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetCustomerIDGrpc.GetCustomerIDBlockingStub getCustomerIDBlockingStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.CUSTOMER_SERVER_PORT).usePlaintext().build();
        return GetCustomerIDGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetProductByIdGrpc.GetProductByIdBlockingStub getProductByIdBlockingStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.PRODUCT_SERVER_PORT).usePlaintext().build();
        return GetProductByIdGrpc.newBlockingStub(channel);
    }

}