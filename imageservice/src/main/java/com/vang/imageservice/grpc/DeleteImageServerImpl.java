package com.vang.imageservice.grpc;

import com.amazonaws.services.s3.AmazonS3;
import com.vang.imageservice.grpc.gen.DeleteImageReply;
import com.vang.imageservice.grpc.gen.DeleteImageRequest;
import com.vang.imageservice.grpc.gen.DeleteImageGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class DeleteImageServerImpl extends DeleteImageGrpc.DeleteImageImplBase {

    private final AmazonS3 s3;

    @Autowired
    public DeleteImageServerImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public void delete(DeleteImageRequest request, StreamObserver<DeleteImageReply> responseObserver) {

        s3.deleteObject("mini-microservice",request.getImageName());
        DeleteImageReply reply = DeleteImageReply.newBuilder().setStatus(true).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}