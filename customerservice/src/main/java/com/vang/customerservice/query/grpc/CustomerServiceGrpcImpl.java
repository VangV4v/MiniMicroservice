package com.vang.customerservice.query.grpc;

import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
import com.vang.customerservice.query.model.CustomerResponseModel;
import com.vang.customerservice.query.service.grpc.CustomerServiceGrpc;
import com.vang.customerservice.query.service.grpc.LoginReply;
import com.vang.customerservice.query.service.grpc.LoginRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.BooleanCommon;
import org.vang.minimicroservice.common.SecurityCommon;
import org.vang.minimicroservice.message.MessageCode;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.service.ServiceCommon;

@GrpcService
public class CustomerServiceGrpcImpl extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomersRepository repository;

    @Override
    public void login(LoginRequest request, StreamObserver<LoginReply> responseObserver) {

        //U should change here because should not use more if
        String password = repository.getPasswordByUsername(request.getUsername());
        if(!StringUtils.isBlank(password)) {
            LoginReply reply = LoginReply.newBuilder()
                    .setStatus(BooleanCommon.TRUE)
                    .setResult(ServiceCommon.OK)
                    .setPassword(password)
                    .setRole(SecurityCommon.ROLE_CUSTOMER)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }else {

            LoginReply reply = LoginReply.newBuilder().setStatus(BooleanCommon.FALSE).setResult(MessageCommon.getMessage(MessageCode.CUSTOMER004)).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}