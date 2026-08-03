package com.faifly.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
//@ComponentScan("com.faifly.hospital")
//@PropertySource("classpath:application.yaml")
public class DatasourceConfig {

//    private final DataSource dataSource;

//    @Value("${spring.datasource.driver-class-name}")
//    private String driver;
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String user;
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
//        dataSource.setDriverClassName(driver);
//        return dataSource;
//    }

    @Bean
    public ResourceDatabasePopulator databasePopulator(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("/sql/schema.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);
        return populator;
    }


    @Bean
    public TransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
