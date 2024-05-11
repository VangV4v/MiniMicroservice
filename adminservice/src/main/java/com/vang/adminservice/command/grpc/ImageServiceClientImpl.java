package com.vang.adminservice.command.grpc;

import com.google.protobuf.ByteString;
import com.vang.adminservice.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub;
    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageServiceClientImpl(UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.uploadImageBlockingStub = uploadImageBlockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String sendUploadRequestImage(ByteString image, String fileName) {

        UploadReply reply = uploadImageBlockingStub.upload(UploadRequest.newBuilder().setImage(image).setImageName(fileName).setType(3).build());
        return reply.getImageUrl();
    }

    public void sendDeleteRequestImage(String imageName) {
        DeleteImageReply reply = deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageName).build());
    }
}
