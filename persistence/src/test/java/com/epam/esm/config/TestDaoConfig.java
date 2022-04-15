package com.epam.esm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.epam.esm")
@Profile("test")
public class TestDaoConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)                 // todo delete
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
//                .ignoreFailedDrops(true)
                .addScript("sql/db_test_schema.sql")
                .addScripts("sql/db_test_data.sql")
                .build();

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

