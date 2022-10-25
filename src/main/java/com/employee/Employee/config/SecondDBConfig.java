package com.employee.Employee.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManager",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"com.employee.Employee.secondaryDB"}
)
public class SecondDBConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.seconddatasource")
    public DataSource secondaryDataSource(){
        return  DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManager(EntityManagerFactoryBuilder builder){
       return builder.dataSource(secondaryDataSource())
               .packages("com.employee.Employee.secondaryDB")
               .build();

    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondEntityManager") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
