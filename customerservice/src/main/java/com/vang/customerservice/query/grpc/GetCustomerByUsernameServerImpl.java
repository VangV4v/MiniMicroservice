package com.vang.customerservice.query.grpc;

import com.google.gson.Gson;
import com.vang.customerservice.data.Customers;
import com.vang.customerservice.data.CustomersRepository;
import com.vang.customerservice.query.grpcmodel.CustomerJsonModel;
import com.vang.customerservice.query.service.grpc.GetCustomerByUsernameGrpc;
import com.vang.customerservice.query.service.grpc.GetCustomerByUsernameReply;
import com.vang.customerservice.query.service.grpc.GetCustomerByUsernameRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetCustomerByUsernameServerImpl extends GetCustomerByUsernameGrpc.GetCustomerByUsernameImplBase {

    private final CustomersRepository customersRepository;

    @Autowired
    public GetCustomerByUsernameServerImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public void getData(GetCustomerByUsernameRequest request, StreamObserver<GetCustomerByUsernameReply> responseObserver) {
        Gson gson = new Gson();
        Customers customers = customersRepository.findByUsername(request.getUsername());
        CustomerJsonModel customerJsonModel = new CustomerJsonModel();
        BeanUtils.copyProperties(customers, customerJsonModel);
        String response = gson.toJson(customerJsonModel);
        GetCustomerByUsernameReply reply = GetCustomerByUsernameReply.newBuilder().setResponse(response).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}