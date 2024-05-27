package com.vang.imageservice.grpcmodel;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ProductImageJsonModel implements Serializable {

    private String defaultimage;
    private byte[] defaultImageByte;
    private String image1;
    private byte[] image1Byte;
    private String image2;
    private byte[] image2Byte;
    private String image3;
    private byte[] image3Byte;
    private String image4;
    private byte[] image4Byte;
    private String image5;
    private byte[] image5Byte;
    private String image6;
    private byte[] image6Byte;
    private String image7;
    private byte[] image7Byte;
    private String image8;
    private byte[] image8Byte;
    private String image9;
    private byte[] image9Byte;
    private String image10;
    private byte[] image10Byte;
}