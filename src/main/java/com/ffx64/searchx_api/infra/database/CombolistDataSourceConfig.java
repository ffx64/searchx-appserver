package com.ffx64.searchx_api.infra.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ffx64.searchx_api.repository.combolist",
    entityManagerFactoryRef = "combolistEntityManagerFactory",
    transactionManagerRef = "combolistTransactionManager"
)
public class CombolistDataSourceConfig {

    @Bean(name="combolistDataSource")
    public DataSource combolistDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5433/searchx_combolist")
            .username("docker")
            .password("docker")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Bean(name="combolistEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean combolistEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("combolistDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.ffx64.searchx_api.entity.combolist")
            .persistenceUnit("combolist")
            .build();
    }

    @Bean(name="combolistTransactionManager")
    public PlatformTransactionManager combolistTransactionManager(
            @Qualifier("combolistEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
