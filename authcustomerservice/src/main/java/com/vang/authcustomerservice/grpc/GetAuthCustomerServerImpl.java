package com.vang.authcustomerservice.grpc;

import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoGrpc;
import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoReply;
import com.vang.authcustomerservice.grpc.gen.GetAuthCustomerInfoRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.vang.minimicroservice.service.FieldNameCommon;

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
        Date stringExpiration = redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_CUSTOMER_EXPIRATION) != null ? new Date(Long.parseLong(redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_CUSTOMER_EXPIRATION))) : new Date();
        String username = redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_CUSTOMER);
        GetAuthCustomerInfoReply reply;
        if(current.before(stringExpiration) && !StringUtils.isEmpty(username)) {
            reply = GetAuthCustomerInfoReply.newBuilder().setStatus(true).setUsername(username).build();
        } else {
            reply = GetAuthCustomerInfoReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}