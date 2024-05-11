package org.vang.minimicroservice.common;

public class ImageServiceCommon {

    public static final String BUCKET_NAME = "mini-microservice";

    public static String getImageKey(String urlImage) {
        int firstIndex = urlImage.lastIndexOf(StringCommon.CHARACTER_SPECIAL_001)-1;
        int index = urlImage.lastIndexOf(StringCommon.CHARACTER_SPECIAL_001,firstIndex);
        return urlImage.substring(index+1);
    }
}
