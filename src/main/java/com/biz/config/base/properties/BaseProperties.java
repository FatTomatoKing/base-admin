package com.biz.config.base.properties;

import com.biz.config.base.properties.nested.CaptchaProperties;
import com.biz.config.base.properties.nested.BaseFlywayProperties;
import com.biz.config.base.properties.nested.FileUploadProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author suyh
 * @since 2024-08-28
 */
@ConfigurationProperties(prefix = BaseProperties.PREFIX)
@Validated
@Data
public class BaseProperties {
    public static final String PREFIX = "biz.base";

    /**
     * token 的有效时间
     */
    private Integer tokenSeconds = 30 * 60;

    @NestedConfigurationProperty
    @Valid
    private BaseFlywayProperties flywayMysqlBase = new BaseFlywayProperties();

    @NestedConfigurationProperty
    @Valid
    private BaseFlywayProperties flywayPgsqlBase = new BaseFlywayProperties();

    @NestedConfigurationProperty
    @Valid
    private FileUploadProperties fileUpload = new FileUploadProperties();

    @NestedConfigurationProperty
    @Valid
    private CaptchaProperties captcha = new CaptchaProperties();
}
