package com.javaMentors.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class WebConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        lcemfb.setDataSource(getDataSource());
        lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
        lcemfb.setPackagesToScan("com.javaMentors.model");
        lcemfb.setJpaProperties(properties());
        return  lcemfb;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }


    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.driver"));
        dataSource.setUrl(env.getProperty("mysql.url"));
        dataSource.setUsername(env.getProperty("mysql.user"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put(DIALECT, env.getProperty("hibernate.dialect"));
        properties.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        return properties;
    }
}
