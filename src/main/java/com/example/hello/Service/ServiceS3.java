package com.example.hello.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@PropertySource("classpath:application-aws.yml")
public class ServiceS3 {

    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    //새 파일 업로드드
   public String upload(MultipartFile file) throws IOException {

        long time = System.currentTimeMillis();

        String uploadName =  time + file.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucket, uploadName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        //이미지 url를 리턴으로 내보낸다.
        return s3Client.getUrl(bucket, uploadName).toString();
    }

    //오브젝트 삭제
   public void delete(String src){

        String url = "https://ourclothing.s3.ap-northeast-2.amazonaws.com/";

        String key= src.substring(url.length());

        s3Client.deleteObject(new DeleteObjectRequest(bucket, key));
    }
}