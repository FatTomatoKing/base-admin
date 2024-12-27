package com.biz.config.datasource.properties;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.biz.constant.DataSourceNames;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suyh
 * @since 2024-03-20
 */
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Data
@Validated
public class DynamicDataSourceProviderProperties implements DynamicDataSourceProvider {
    @NotNull
    @NestedConfigurationProperty
    private HikariDataSource mysqlBase;

    @NestedConfigurationProperty
    private HikariDataSource pgsqlBase;

    private Map<String, DataSource> mapDatasource = new HashMap<>();

    @PostConstruct
    public void init() {
        mapDatasource.put(DataSourceNames.MYSQL_BASE, mysqlBase);
        if (pgsqlBase != null) {
            mapDatasource.put(DataSourceNames.CDS_PGSQL, pgsqlBase);
        }
    }

    @Override
    public synchronized Map<String, DataSource> loadDataSources() {
        return mapDatasource;
    }
}
