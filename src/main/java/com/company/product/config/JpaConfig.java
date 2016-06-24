package com.company.product.config;

import org.apache.commons.dbcp.BasicDataSource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;


@Configuration
@ComponentScan(basePackages = "com.company.product")
@EnableTransactionManagement
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:tcp://localhost/~/tmp/testdb");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        ds.setInitialSize(5);
//        ds.setMaxActive(10);
//        return ds;
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
//                .addScript("classpath:data.sql")
                .build();

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setPackagesToScan("com.company.product.model");

        Properties props = new Properties();
//        props.setProperty("hibernate.hbm2ddl.auto", "create");
        props.setProperty("hibernate.format_sql", "true");
        emf.setJpaProperties(props);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
