package com.vang.testgrpcclient.service;

import com.vang.testgrpcclient.serivce.grpc.CustomerServiceGrpc;
import com.vang.testgrpcclient.serivce.grpc.LoginReply;
import com.vang.testgrpcclient.serivce.grpc.LoginRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceClient {

    @GrpcClient("CustomerService")
    private CustomerServiceGrpc.CustomerServiceBlockingStub stub;

    public String loginRequest(String username) {

//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",2009).usePlaintext().build();

//        CustomerServiceGrpc.CustomerServiceBlockingStub blockingStub = CustomerServiceGrpc.newBlockingStub(channel);
        LoginReply reply = stub.login(LoginRequest.newBuilder().setUsername(username).build());
        return reply.getResult();
    }
}
