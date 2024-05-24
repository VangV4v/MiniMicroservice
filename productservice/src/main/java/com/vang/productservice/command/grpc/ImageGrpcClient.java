package com.vang.productservice.command.grpc;

import com.google.gson.Gson;
import com.vang.productservice.command.grpcmodel.ProductImageJsonModel;
import com.vang.productservice.grpc.gen.UploadMultiImageGrpc;
import com.vang.productservice.grpc.gen.UploadMultiImageReply;
import com.vang.productservice.grpc.gen.UploadMultiImageRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageGrpcClient {

    private final UploadMultiImageGrpc.UploadMultiImageBlockingStub uploadMultiImageBlockingStub;
    private final Gson gson = new Gson();

    @Autowired
    public ImageGrpcClient(UploadMultiImageGrpc.UploadMultiImageBlockingStub uploadMultiImageBlockingStub) {
        this.uploadMultiImageBlockingStub = uploadMultiImageBlockingStub;
    }

    public ProductImageJsonModel uploadMultiImage(ProductImageJsonModel jsonModel, String productId) {

        String convertToRequestJson = gson.toJson(jsonModel);
        UploadMultiImageReply reply = uploadMultiImageBlockingStub.upload(UploadMultiImageRequest.newBuilder().setImageName(productId).setJsonRequest(convertToRequestJson).build());
        if(StringUtils.isBlank(reply.getJsonResponse())) {
            throw new NullPointerException("json upload response is null");
        }
        jsonModel = gson.fromJson(reply.getJsonResponse(), ProductImageJsonModel.class);
        return jsonModel;
    }
}