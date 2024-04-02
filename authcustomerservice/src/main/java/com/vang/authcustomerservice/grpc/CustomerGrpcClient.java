package com.vang.authcustomerservice.grpc;

import com.vang.authcustomerservice.service.grpc.CustomerServiceGrpc;
import com.vang.authcustomerservice.service.grpc.LoginReply;
import com.vang.authcustomerservice.service.grpc.LoginRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerGrpcClient {

    @GrpcClient("CustomerService")
    private CustomerServiceGrpc.CustomerServiceBlockingStub stub;

    public Map<String, Object> getReplyLogin(String username) {

        Map<String, Object> result = new HashMap<>();
        LoginReply reply = stub.login(LoginRequest.newBuilder()
                .setUsername(username)
                .build());
        if(reply.getStatus()) {

            result.put(FieldNameCommon.STATUS,true);
            result.put(FieldNameCommon.PASSWORD,reply.getPassword());
            result.put(FieldNameCommon.ROLE, reply.getRole());
        }else {

            result.put(FieldNameCommon.STATUS,false);
        }
        return result;
    }

}