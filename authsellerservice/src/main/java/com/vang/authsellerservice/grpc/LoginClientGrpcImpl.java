package com.vang.authsellerservice.grpc;

import com.vang.authsellerservice.grpc.gen.LoginReply;
import com.vang.authsellerservice.grpc.gen.LoginRequest;
import com.vang.authsellerservice.grpc.gen.LoginSellerGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginClientGrpcImpl {

    @GrpcClient("LoginSeller")
    private LoginSellerGrpc.LoginSellerBlockingStub loginSellerBlockingStub;

    public Map<String, Object> checkLogin(String username) {
        Map<String, Object> result = new HashMap<>();
        LoginReply reply = loginSellerBlockingStub.login(LoginRequest.newBuilder().setUsername(username).build());
        if(reply.getStatus()) {
            result.put(FieldNameCommon.PASSWORD, reply.getPassword());
        }
        return result;
    }
}