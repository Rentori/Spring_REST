package com.rentori.spring_rest.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AwsS3 {
    private final String accessKey = "AKIAZYQLEUZ3HZVRUCGV";
    private final String secretKey = "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";
    private final String bucketName = "springfiles";
    private final AmazonS3 s3client;
    
    public AwsS3() {
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(initCredentials()))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }
    
    private AWSCredentials initCredentials() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );
        
        return awsCredentials;
    }
    
    public void putFile(String fileName, File file) {
        s3client.putObject(bucketName, "Files/" + fileName, file);
    }
    
    @SneakyThrows
    public void updateFile(String fileName, String newFileName) {
        s3client.deleteObject(bucketName, "Files/" + fileName);
        java.io.File file = new java.io.File("C:\\Users\\vitya\\" + newFileName);
        file.createNewFile();
        putFile(newFileName, file);
    }
    
    public void deleteFile(String fileName) {
        s3client.deleteObject(bucketName, "Files/" + fileName);
    }
}
