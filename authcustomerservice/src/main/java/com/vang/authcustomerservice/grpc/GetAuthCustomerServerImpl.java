package com.vang.authcustomerservice.grpc;

import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoGrpc;
import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoReply;
import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@GrpcService
public class GetAuthCustomerServerImpl extends GetAuthCustomerInfoGrpc.GetAuthCustomerInfoImplBase {

    private final RedisTemplate<String, String> redisTemplate;

    public GetAuthCustomerServerImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void getInfo(GetAuthCustomerInfoRequest request, StreamObserver<GetAuthCustomerInfoReply> responseObserver) {

        Date current = new Date();
        String stringExpiration = redisTemplate.opsForValue().get("usernameExpiration") != null ? redisTemplate.opsForValue().get("usernameExpiration") : null;
        GetAuthCustomerInfoReply reply;
        if(stringExpiration != null && current.before(new Date(Long.parseLong(stringExpiration)))) {
            String username = redisTemplate.opsForValue().get("username");
            reply = GetAuthCustomerInfoReply.newBuilder().setStatus(true).setUsername(username).build();
        } else {
            reply = GetAuthCustomerInfoReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}