package com.vang.brandservice.configuation;

import com.vang.brandservice.command.grpc.gen.DeleteImageGrpc;
import com.vang.brandservice.command.grpc.gen.UploadImageGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vang.minimicroservice.common.GrpcCommon;

@Configuration
public class GrpcConfiguation {

    @Bean
    public UploadImageGrpc.UploadImageBlockingStub initBlockingStubUploadImage() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST,GrpcCommon.PortCommon.IMAGE_SERVER_PORT).usePlaintext().build();
        return UploadImageGrpc.newBlockingStub(channel);
    }

    @Bean
    public DeleteImageGrpc.DeleteImageBlockingStub initBlockingStubDeleteImage() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(GrpcCommon.IP_HOST,GrpcCommon.PortCommon.IMAGE_SERVER_PORT).usePlaintext().build();
        return DeleteImageGrpc.newBlockingStub(channel);
    }
}
