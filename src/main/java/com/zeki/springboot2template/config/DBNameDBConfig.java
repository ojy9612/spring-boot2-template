package com.zeki.springboot2template.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;


//@Configuration    // TODO
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "dbNameEntityManager",
        transactionManagerRef = "dbNameTransactionManager",
        basePackages = "com.") // TODO
@RequiredArgsConstructor
public class DBNameDBConfig {
    private final Environment env;

    @Bean
    public DataSource operaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.db-name.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.db-name.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.db-name.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.db-name.datasource.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean operaEntityManager() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String, Object> properties = new HashMap<>();
        localContainerEntityManagerFactoryBean.setDataSource(operaDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com."); // TODO
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.main.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.main.hibernate.dialect"));
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager operaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(operaEntityManager().getObject());
        return transactionManager;
    }
}

