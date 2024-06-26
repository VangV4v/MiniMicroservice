package com.vang.authsellerservice.grpc;

import com.vang.authsellerservice.grpc.gen.GetAuthSellerInfoGrpc;
import com.vang.authsellerservice.grpc.gen.GetAuthSellerInfoReply;
import com.vang.authsellerservice.grpc.gen.GetAuthSellerInfoRequest;
import com.vang.authsellerservice.jwt.MyUserdetailsService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.Date;

@GrpcService
public class GetAuthSellerServerImpl extends GetAuthSellerInfoGrpc.GetAuthSellerInfoImplBase {

    private final MyUserdetailsService userdetailsService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public GetAuthSellerServerImpl(MyUserdetailsService userdetailsService, RedisTemplate<String, String> redisTemplate) {
        this.userdetailsService = userdetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void getInfo(GetAuthSellerInfoRequest request, StreamObserver<GetAuthSellerInfoReply> responseObserver) {

        GetAuthSellerInfoReply reply;
        String username = redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_SELLER);
        Date usernameExpiration = redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_SELLER_EXPIRATION) != null ? new Date(Long.parseLong(redisTemplate.opsForValue().get(FieldNameCommon.USERNAME_SELLER_EXPIRATION))) : new Date();
        if(!ObjectUtils.isEmpty(username) && new Date().before(usernameExpiration)) {
            reply = GetAuthSellerInfoReply.newBuilder().setUsername(username).setStatus(true).build();
        } else {
            reply = GetAuthSellerInfoReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}