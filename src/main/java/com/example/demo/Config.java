package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost;database=devops;integratedSecurity=false;user=<YourUserName>;password=<YourPassword>;encrypt=true;trustServerCertificate=true;");
        dataSource.setUsername("<YourUserName>");
        dataSource.setPassword("<YourPassword>");
        return dataSource;

    }
}

