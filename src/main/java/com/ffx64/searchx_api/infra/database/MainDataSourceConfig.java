package com.ffx64.searchx_api.infra.database;

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
    basePackages="com.ffx64.searchx_api.repository.main",
    entityManagerFactoryRef="mainEntityManagerFactory",
    transactionManagerRef="mainTransactionManager"
)
public class MainDataSourceConfig {
    
    @Primary
    @Bean(name="mainDataSource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5435/searchx")
            .username("docker")
            .password("docker")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Primary
    @Bean(name="mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mainDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.ffx64.searchx_api.entity.main")
            .persistenceUnit("main")
            .build();
    }

    @Primary
    @Bean(name="mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("mainEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
