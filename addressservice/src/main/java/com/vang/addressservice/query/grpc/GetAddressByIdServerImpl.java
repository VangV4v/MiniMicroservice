package com.vang.addressservice.query.grpc;

import com.google.gson.Gson;
import com.vang.addressservice.command.grpc.gen.GetAddressByIdGrpc;
import com.vang.addressservice.command.grpc.gen.GetAddressByIdReply;
import com.vang.addressservice.command.grpc.gen.GetAddressByIdRequest;
import com.vang.addressservice.data.Addresses;
import com.vang.addressservice.data.AddressesRepository;
import com.vang.addressservice.query.grpcmodel.AddressJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetAddressByIdServerImpl extends GetAddressByIdGrpc.GetAddressByIdImplBase {

    private final AddressesRepository addressesRepository;

    @Autowired
    public GetAddressByIdServerImpl(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @Override
    public void getData(GetAddressByIdRequest request, StreamObserver<GetAddressByIdReply> responseObserver) {
        Gson gson = new Gson();
        Addresses addresses = addressesRepository.findById(request.getAddressId()).get();
        AddressJsonModel addressJsonModel = new AddressJsonModel();
        BeanUtils.copyProperties(addresses, addressJsonModel);
        String response = gson.toJson(addressJsonModel);
        GetAddressByIdReply reply = GetAddressByIdReply.newBuilder().setResponse(response).setStatus(Boolean.TRUE).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}