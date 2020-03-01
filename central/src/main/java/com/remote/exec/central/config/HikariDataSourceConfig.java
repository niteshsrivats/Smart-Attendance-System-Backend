package com.remote.exec.central.config;

import com.remote.exec.central.named.Properties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Configuration
public class HikariDataSourceConfig {

    @Bean
    @ConfigurationProperties(Properties.Hikari)
    @Primary
    public DataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
