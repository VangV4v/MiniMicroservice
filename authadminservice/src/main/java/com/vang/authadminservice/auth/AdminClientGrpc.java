package com.vang.authadminservice.auth;

import com.vang.authadminservice.auth.grpc.AdminServiceGrpc;
import com.vang.authadminservice.auth.grpc.LoginAdminReply;
import com.vang.authadminservice.auth.grpc.LoginAdminRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminClientGrpc {

    @GrpcClient("AdminService")
    private AdminServiceGrpc.AdminServiceBlockingStub stub;

    public Map<String, String> responseLoginAdmin(String username) {

        LoginAdminReply reply = stub.login(LoginAdminRequest.newBuilder().setUsername(username).build());
        return null;
    }
}