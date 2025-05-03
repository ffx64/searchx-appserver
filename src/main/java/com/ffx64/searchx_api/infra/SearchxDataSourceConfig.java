package com.ffx64.searchx_api.infra;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ffx64.searchx_api.repository.searchx",
    entityManagerFactoryRef = "searchxEntityManagerFactory",
    transactionManagerRef = "searchxTransactionManager"
)
public class SearchxDataSourceConfig {
    
    @Primary
    @Bean(name="searchxDataSource")
    public DataSource searchxDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://192.168.229.131:5435/searchx")
            .username("docker")
            .password("docker")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Primary
    @Bean(name="searchxEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean searchxEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("searchxDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.ffx64.searchx_api.entity.searchx")
            .persistenceUnit("searchx")
            .build();
    }

    @Primary
    @Bean(name="searchxTransactionManager")
    public PlatformTransactionManager searchxTransactionManager(
            @Qualifier("searchxEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
