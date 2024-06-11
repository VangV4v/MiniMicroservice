package com.vang.confirmorderservice.configuation;

import com.vang.confirmorderservice.grpc.gen.GetAuthSellerInfoGrpc;
import com.vang.confirmorderservice.grpc.gen.GetSellerByUsernameGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vang.minimicroservice.common.GrpcCommon;

@Configuration
public class GrpcConfiguation {

    @Bean
    public GetAuthSellerInfoGrpc.GetAuthSellerInfoBlockingStub getAuthSellerInfoBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.AUTH_SELLER_SERVER_PORT).usePlaintext().build();
        return GetAuthSellerInfoGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetSellerByUsernameGrpc.GetSellerByUsernameBlockingStub getSellerByUsernameBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.SELLER_SERVER_PORT).usePlaintext().build();
        return GetSellerByUsernameGrpc.newBlockingStub(channel);
    }
}