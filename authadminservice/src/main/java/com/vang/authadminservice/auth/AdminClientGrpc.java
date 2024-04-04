package com.vang.authadminservice.auth;

import com.vang.authadminservice.auth.grpc.AdminServiceGrpc;
import com.vang.authadminservice.auth.grpc.LoginAdminReply;
import com.vang.authadminservice.auth.grpc.LoginAdminRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminClientGrpc {

    @GrpcClient("AdminService")
    private AdminServiceGrpc.AdminServiceBlockingStub stub;

    public Map<String, Object> responseLoginAdmin(String username) {

        Map<String, Object> result = new HashMap<>();
        LoginAdminReply reply = stub.login(LoginAdminRequest.newBuilder().setUsername(username).build());
        if(reply.getStatus()) {
            result.put(FieldNameCommon.ROLE, reply.getRole());
            result.put(FieldNameCommon.PASSWORD, reply.getPassword());
            result.put(FieldNameCommon.STATUS, reply.getStatus());
        }else {
            result.put(FieldNameCommon.STATUS, reply.getStatus());
        }
        return result;
    }
}