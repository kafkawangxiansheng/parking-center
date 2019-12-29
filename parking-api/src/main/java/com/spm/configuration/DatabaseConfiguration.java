package com.spm.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Database configuration.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/spm_parking?useUnicode=true&amp;characterEncoding=utf-8}")
    private String url;
    @Value("${spring.datasource.username:root}")
    private String username;
    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        return dataSource;
    }
}