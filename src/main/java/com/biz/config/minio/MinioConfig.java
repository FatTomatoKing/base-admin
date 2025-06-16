package com.biz.config.minio;


import com.biz.config.minio.properties.BasicMinioProperties;
import io.minio.MinioClient;
import io.minio.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties; // 导入这个注解

@Configuration
@EnableConfigurationProperties(BasicMinioProperties.class) // 启用 MinioProperties 配置类
@RequiredArgsConstructor
public class MinioConfig {

    private final BasicMinioProperties basicMinioProperties;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(basicMinioProperties.getUrl()) // 使用 MinioProperties 获取 url
                .credentials(basicMinioProperties.getAccessKey(), basicMinioProperties.getSecretKey()) // 获取 accessKey 和 secretKey
                .build();
    }
}
