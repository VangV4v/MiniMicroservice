package com.vang.imageservice.configuation;

//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguation {

    private static final String ACCESS_KEY = "AKIA4MTWILGU4UQLB6XF";
    private static final String SECRET_KEY = "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";
    private static final String REGION = "ap-southeast-2";

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}