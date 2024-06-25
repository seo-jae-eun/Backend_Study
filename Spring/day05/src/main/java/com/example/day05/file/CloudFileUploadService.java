package com.example.day05.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;

@Service
public class CloudFileUploadService implements FileUploadService{
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${cloud.aws.region.static}")
    private String region;
    private final AmazonS3 amazonS3;

    public CloudFileUploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String upload(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // https://backend2024-s3.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD.PNG
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, file.getOriginalFilename());
//        return amazonS3.getUrl();
    }
}
