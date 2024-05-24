package com.vang.sellerservice.query.grpc;

import com.vang.sellerservice.data.SellersRepository;
import com.vang.sellerservice.query.service.grpc.LoginReply;
import com.vang.sellerservice.query.service.grpc.LoginRequest;
import com.vang.sellerservice.query.service.grpc.LoginSellerGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class LoginSellerGrpcImpl extends LoginSellerGrpc.LoginSellerImplBase {

    private final SellersRepository sellersRepository;

    @Autowired
    public LoginSellerGrpcImpl(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginReply> responseObserver) {

        String checkPassword = sellersRepository.getPasswordByUsername(request.getUsername());
        LoginReply reply;
        if (!StringUtils.isBlank(checkPassword)) {
            reply = LoginReply.newBuilder().setStatus(true).setPassword(checkPassword).build();
        }else {
            reply = LoginReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
