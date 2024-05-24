package com.vang.sellerservice.command.grpc;

import com.google.protobuf.ByteString;
import com.vang.sellerservice.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.NumberUtils;

import java.io.IOException;

@Service
public class ImageServiceClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub;

    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageServiceClientImpl(UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.uploadImageBlockingStub = uploadImageBlockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String sendRequestUploadImage(ByteString image, String fileName) {
        UploadReply uploadReply = uploadImageBlockingStub.upload(UploadRequest.newBuilder().setImage(image).setType(NumberUtils.FOUR).setImageName(fileName).build());
        return uploadReply.getImageUrl();
    }

    public boolean sendRequestRemoveImage(String imageName) {

        DeleteImageReply reply = deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageName).build());
        return reply.getStatus();
    }
}