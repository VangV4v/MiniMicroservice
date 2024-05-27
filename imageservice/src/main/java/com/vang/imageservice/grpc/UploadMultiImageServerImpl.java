package com.vang.imageservice.grpc;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.gson.Gson;
import com.vang.imageservice.grpc.gen.UploadMultiImageGrpc;
import com.vang.imageservice.grpc.gen.UploadMultiImageReply;
import com.vang.imageservice.grpc.gen.UploadMultiImageRequest;
import com.vang.imageservice.grpcmodel.ProductImageJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.vang.minimicroservice.common.ImageDefaultCommon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

@GrpcService
public class UploadMultiImageServerImpl extends UploadMultiImageGrpc.UploadMultiImageImplBase {

    private final AmazonS3 s3;
    private final Gson gson = new Gson();

    @Autowired
    public UploadMultiImageServerImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public void upload(UploadMultiImageRequest request, StreamObserver<UploadMultiImageReply> responseObserver) {

        String jsonRequest = request.getJsonRequest();
        ProductImageJsonModel model = gson.fromJson(jsonRequest, ProductImageJsonModel.class);
        if(!ObjectUtils.isEmpty(model.getDefaultImageByte())) {
            model.setDefaultimage(uploadImage(model.getDefaultImageByte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage1Byte())) {
            model.setImage1(uploadImage(model.getImage1Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage2Byte())) {
            model.setImage2(uploadImage(model.getImage2Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage3Byte())) {
            model.setImage3(uploadImage(model.getImage3Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage4Byte())) {
            model.setImage4(uploadImage(model.getImage4Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage5Byte())) {
            model.setImage5(uploadImage(model.getImage5Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage6Byte())) {
            model.setImage6(uploadImage(model.getImage6Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage7Byte())) {
            model.setImage7(uploadImage(model.getImage7Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage8Byte())) {
            model.setImage8(uploadImage(model.getImage8Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage9Byte())) {
            model.setImage9(uploadImage(model.getImage9Byte(), request.getImageName()));
        }
        if(!ObjectUtils.isEmpty(model.getImage10Byte())) {
            model.setImage10(uploadImage(model.getImage10Byte(), request.getImageName()));
        }
        String jsonResponse = gson.toJson(model);
        UploadMultiImageReply reply = UploadMultiImageReply.newBuilder().setJsonResponse(jsonResponse).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    public String uploadImage(byte[] image, String productId) {
        String imageName = "products/"+(System.currentTimeMillis())+"product_"+productId+(LocalDate.now().toString())+".jpg";
        InputStream inputStream = new ByteArrayInputStream(image);
        PutObjectRequest objectRequest = new PutObjectRequest(ImageDefaultCommon.BUCKET, imageName, inputStream, null).withCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(objectRequest);
        return ImageDefaultCommon.PRE_URL_IMAGE +imageName;
    }

}