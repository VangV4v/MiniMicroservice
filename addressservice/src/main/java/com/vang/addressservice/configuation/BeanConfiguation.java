package com.vang.addressservice.configuation;

import com.vang.addressservice.command.grpc.gen.GetCustomerIDGrpc;
import com.vang.addressservice.grpc.gen.GetAuthCustomerInfoGrpc;
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

}