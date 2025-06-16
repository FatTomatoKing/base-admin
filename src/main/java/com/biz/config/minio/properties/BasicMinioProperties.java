package com.biz.config.minio.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component // 或者不需要，取决于你如何使用这个类
@ConfigurationProperties(prefix = "minio") // 绑定 application.yml 中以 "minio" 开头的配置
public class BasicMinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}