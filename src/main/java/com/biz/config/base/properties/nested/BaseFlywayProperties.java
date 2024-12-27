package com.biz.config.base.properties.nested;

import lombok.Data;

/**
 * @author suyh
 * @since 2024-02-23
 */
@Data
public class BaseFlywayProperties {
    private boolean enabled = false;

    private String[] locations;
}
