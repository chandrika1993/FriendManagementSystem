package com.friendmanagement.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.friendmanagement.constants.FriendsConstants;

/**
 * <PRE>
 * Class name       : MicroServiceConfiguration
 * Description      : Provides bean definitions for the micro service
 * Author           : Capgemini.
 * </PRE>
 */
@Configuration
@EnableJpaRepositories(basePackages = { FriendsConstants.FRIENDS_DAO_PACKAGE })
public class MicroServiceConfiguration {

    /**
     * The meta data bean consisting of properties read from the yml
     * configuration file.
     */
    @Value("${dbUsername}")
    private String dbUsername;

    @Value("${dbPassword}")
    private String dbPassword;

    @Value("${dbServerUrl}")
    private String dbServerUrl;

    @Value("${dbDriverClass}")
    private String dbDriverClass;

    @Value("${dbServerDialect}")
    private String dbServerDialect;

    /**
     * 
     * @return DriverManagerDataSource
     */
    @Bean(name = "datasource")
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();
        driverManagerDataSource.setUsername(dbUsername);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setUrl(dbServerUrl);
        driverManagerDataSource.setDriverClassName(dbDriverClass);
        return driverManagerDataSource;
    }

    /**
     * 
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform(dbServerDialect);
        localContainerEntityManagerFactoryBean
                .setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean
                .setDataSource(getDriverManagerDataSource());
        localContainerEntityManagerFactoryBean
                .setPackagesToScan(FriendsConstants.FRIENDS_MODELPACKAGE);
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * 
     * @param entityManagerFactory
     * @return
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager getJpaTransactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
