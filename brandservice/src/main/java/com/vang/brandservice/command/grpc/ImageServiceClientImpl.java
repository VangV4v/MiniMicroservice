package com.vang.brandservice.command.grpc;

import com.google.protobuf.ByteString;
import com.vang.brandservice.command.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub blockingStub;
    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageServiceClientImpl(UploadImageGrpc.UploadImageBlockingStub blockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.blockingStub = blockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String sendRequestUploadImage(ByteString image, String fileName) {
        UploadReply  uploadReply = blockingStub.upload(UploadRequest.newBuilder().setImage(image).setType(1).setImageName(fileName).build());
        return uploadReply.getImageUrl();
    }

    public boolean sendRequestRemoveImage(String imageName) {

        DeleteImageReply reply = deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageName).build());
        return reply.getStatus();
    }
}