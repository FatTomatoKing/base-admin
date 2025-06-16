package com.biz.business.controller;


import com.biz.config.minio.properties.BasicMinioProperties;
import com.biz.mvc.authentication.annotation.Permit;
import com.biz.mvc.vo.ResponseResult;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {


    private final MinioClient minioClient;

    private final BasicMinioProperties basicMinioProperties; // 注入 MinioProperties 是正确的

    /**
     * 上传文件到MinIO并返回文件访问URL
     * POST /upload/file
     *
     * @param file 上传的文件
     * @return 包含文件URL的ResponseResult
     */
    @PostMapping("/file")
    @Permit(required = false)
    public ResponseResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String bucketName = basicMinioProperties.getBucketName();
            String minioUrl = basicMinioProperties.getUrl();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                System.out.println("Bucket '" + bucketName + "' created successfully.");
            } else {
                System.out.println("Bucket '" + bucketName + "' already exists.");
            }

            String originalFilename = file.getOriginalFilename();
            String objectName = UUID.randomUUID().toString().replace("-", "") + "_" + originalFilename;
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            String fileUrl = minioUrl + "/" + bucketName + "/" + objectName;
            return ResponseResult.ofSuccess(fileUrl); // 假设 ofSuccess(data) 是正确的

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResponseResult.ofSuccess();
    }
}