package com.vang.adminservice.query.grpc;

import com.vang.adminservice.data.Admins;
import com.vang.adminservice.data.AdminsRepository;
import com.vang.adminservice.query.service.grpc.AdminServiceGrpc;
import com.vang.adminservice.query.service.grpc.LoginAdminReply;
import com.vang.adminservice.query.service.grpc.LoginAdminRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.BooleanCommon;
import org.vang.minimicroservice.common.SecurityCommon;
import org.vang.minimicroservice.message.MessageCode;
import org.vang.minimicroservice.message.MessageCommon;
import org.vang.minimicroservice.service.ServiceCommon;

@GrpcService
public class AdminGrpcImpl extends AdminServiceGrpc.AdminServiceImplBase {

    private final AdminsRepository repository;

    @Autowired
    public AdminGrpcImpl(AdminsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void login(LoginAdminRequest request, StreamObserver<LoginAdminReply> responseObserver) {

        String password = repository.getPasswordByUsername(request.getUsername());
        if(StringUtils.isBlank(password)) {
            LoginAdminReply reply = LoginAdminReply.newBuilder().setStatus(BooleanCommon.FALSE).setResult(MessageCommon.getMessage(MessageCode.CUSTOMER004)).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }else {
            LoginAdminReply reply = LoginAdminReply.newBuilder()
                    .setRole(SecurityCommon.ROLE_ADMIN)
                    .setPassword(password)
                    .setResult(ServiceCommon.OK)
                    .setStatus(BooleanCommon.TRUE)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
