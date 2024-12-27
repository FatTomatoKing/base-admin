package com.biz.config.datasource;

import com.biz.config.base.properties.BaseProperties;
import com.biz.config.datasource.properties.DynamicDataSourceProviderProperties;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootConfiguration
@RequiredArgsConstructor
public class MultiDbFlywayConfiguration {

    @ConditionalOnProperty(name = "biz.base.flyway-mysql-base.enabled", havingValue = "true")
    @Bean("cdsMysqlFlyway")
    public FlywayMigrationInitializer cdsMysqlFlyway(
            DynamicDataSourceProviderProperties dynamicDataSourceProviderProperties,
            BaseProperties baseProperties) {
        String[] locations = baseProperties.getFlywayMysqlBase().getLocations();
        FluentConfiguration cdsWebFlywayConfig = new FluentConfiguration();
        cdsWebFlywayConfig.baselineOnMigrate(true)
                .dataSource(dynamicDataSourceProviderProperties.getMysqlBase())
                .locations(locations)
                .table("flyway_schema_history")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(true)
                .outOfOrder(true);
        Flyway cdsWebFlyway = cdsWebFlywayConfig.load();
        return new FlywayMigrationInitializer(cdsWebFlyway, null);
    }

    @ConditionalOnProperty(name = "biz.base.flyway-pgsql-base.enabled", havingValue = "true")
    @Bean("cdsPgsqlFlyway")
    public FlywayMigrationInitializer pgFlyway(
            DynamicDataSourceProviderProperties dynamicDataSourceProviderProperties,
            BaseProperties baseProperties) {
        String[] locations = baseProperties.getFlywayPgsqlBase().getLocations();
        FluentConfiguration cds2FlywayConfig = new FluentConfiguration();
        cds2FlywayConfig.baselineOnMigrate(true)
                .dataSource(dynamicDataSourceProviderProperties.getPgsqlBase())
                .locations(locations)
                .table("flyway_schema_history")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(true)
                .outOfOrder(true);

        Flyway cds2Flyway = cds2FlywayConfig.load();
        return new FlywayMigrationInitializer(cds2Flyway, null);
    }
}
