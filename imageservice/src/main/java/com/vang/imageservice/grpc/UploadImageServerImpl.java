package com.vang.imageservice.grpc;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vang.imageservice.grpc.gen.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vang.minimicroservice.common.ImageDefaultCommon;

import java.io.*;

@GrpcService
public class UploadImageServerImpl extends UploadImageGrpc.UploadImageImplBase {

    private Logger logger = LoggerFactory.getLogger(UploadImageServerImpl.class);

    private final AmazonS3 s3;

    @Autowired
    public UploadImageServerImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public void upload(UploadRequest request, StreamObserver<UploadReply> responseObserver) {

        byte[] image = request.getImage().toByteArray();
        InputStream inputStream = new ByteArrayInputStream(image);
        String imageName = getTypeUpload(request.getType())+System.currentTimeMillis()+request.getImageName();
        PutObjectRequest objectRequest = new PutObjectRequest(ImageDefaultCommon.BUCKET, imageName, inputStream, null).withCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(objectRequest);
        String urlImage = ImageDefaultCommon.PRE_URL_IMAGE +imageName;
        UploadReply uploadReply = UploadReply.newBuilder().setStatus(true).setImageUrl(urlImage).build();
        responseObserver.onNext(uploadReply);
        responseObserver.onCompleted();
    }

    private String getTypeUpload(int typeNumber) {
        if(typeNumber == 1) {
            return ImageDefaultCommon.BRAND_FOLDER;
        }else if(typeNumber == 2) {
            return ImageDefaultCommon.CUSTOMER_FOLDER;
        }else if(typeNumber == 3) {
            return ImageDefaultCommon.ADMIN_FOLDER;
        }else if(typeNumber == 4) {
            return ImageDefaultCommon.SELLERS_FOLDER;
        }
        return "";
    }
}