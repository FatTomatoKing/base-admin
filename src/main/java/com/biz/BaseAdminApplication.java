package com.biz;

import com.biz.bytecodes.MybatisSqlDetailText;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suyh
 * @since 2024-10-21
 */
@SpringBootApplication
public class BaseAdminApplication {
    public static void main(String[] args) {
        MybatisSqlDetailText.rebuildSqlDetail();
        SpringApplication.run(BaseAdminApplication.class);
    }
}
