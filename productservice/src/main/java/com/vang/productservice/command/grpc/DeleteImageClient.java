package com.vang.productservice.command.grpc;

import com.vang.productservice.grpc.gen.DeleteImageGrpc;
import com.vang.productservice.grpc.gen.DeleteImageReply;
import com.vang.productservice.grpc.gen.DeleteImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteImageClient {

    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public DeleteImageClient(DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public boolean deleteImage(String urlImage) {
        DeleteImageReply reply = deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(urlImage).build());
        return reply.getStatus();
    }
}