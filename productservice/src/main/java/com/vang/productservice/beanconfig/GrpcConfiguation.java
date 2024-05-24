package com.vang.productservice.beanconfig;

import com.vang.productservice.command.grpc.brand.gen.GetBrandByIdGrpc;
import com.vang.productservice.command.grpc.gen.GetCategoryByIdGrpc;
import com.vang.productservice.grpc.gen.UploadMultiImageGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vang.minimicroservice.common.GrpcCommon;

@Configuration
public class GrpcConfiguation {

    @Bean
    public GetCategoryByIdGrpc.GetCategoryByIdBlockingStub getCategoryByIdBlockingStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.CATEGORY_SERVER_PORT).usePlaintext().build();
        return GetCategoryByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetBrandByIdGrpc.GetBrandByIdBlockingStub getBrandByIdBlockingStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.BRAND_SERVER_PORT).usePlaintext().build();
        return GetBrandByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public UploadMultiImageGrpc.UploadMultiImageBlockingStub uploadMultiImageBlockingStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST, GrpcCommon.PortCommon.IMAGE_SERVER_PORT).usePlaintext().build();
        return UploadMultiImageGrpc.newBlockingStub(channel);
    }
}