package com.mediscreen.diabetesdetect.patientmanager.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.mediscreen.diabetesdetect.usermanager")
public class SpringJdbcConfig {
    
    @Bean
    public DataSource mySqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/patientdatabase");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }
    
}
