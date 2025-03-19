package com.fashion.serviceimpl;

import com.fashion.repo.ProdRepo;
import com.fashion.response.ProdDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllPhotoServiceImpl {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Autowired
    private ProdRepo prodRepo;


    public List<String> listFiles() {
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response result = s3Client.listObjectsV2(request);

        return result.contents().stream()
                .map(s3Object -> "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + s3Object.key())
                .collect(Collectors.toList());
    }

    public ProdDetails getProdDetails(String id){
       return prodRepo.findProductById(id);
    }

}
