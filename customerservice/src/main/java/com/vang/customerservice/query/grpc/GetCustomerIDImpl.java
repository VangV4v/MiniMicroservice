package com.vang.customerservice.query.grpc;

import com.vang.customerservice.data.CustomersRepository;
import com.vang.customerservice.query.service.grpc.GetCustomerIDGrpc;
import com.vang.customerservice.query.service.grpc.GetCustomerIDReply;
import com.vang.customerservice.query.service.grpc.GetCustomerIDRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetCustomerIDImpl extends GetCustomerIDGrpc.GetCustomerIDImplBase {

    private final CustomersRepository customersRepository;

    @Autowired
    public GetCustomerIDImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public void getInfo(GetCustomerIDRequest request, StreamObserver<GetCustomerIDReply> responseObserver) {
        String customerId = customersRepository.getCustomerIdByUsername(request.getUsername());
        GetCustomerIDReply reply = GetCustomerIDReply.newBuilder().setStatus(true).setResponse(customerId).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}