package com.example.demo;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.friendmanagement.constants.FriendsConstants;

/**
 * <PRE>
 * Class name       : InMemoryDBConfig
 * Description      : In Memory DB Configuration class for testing the DAO Layer. 
 * Author           : Capgemini.
 * </PRE>
 */

@Configuration
@ComponentScan(basePackages = { FriendsConstants.FRIEND_BASE_COMPONENT })
public class InMemoryDBConfig {

    @Bean(name = "datasource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding(FriendsConstants.SCRIPT_ENCODING)
                .ignoreFailedDrops(true)
                .addScript(FriendsConstants.TEST_SQL_SCRIPTS_FILE_PATH
                        + FriendsConstants.TEST_SQL_SCRIPTS_FILE_NAME)
                .build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        localContainerEntityManagerFactoryBean
                .setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean
                .setPackagesToScan(FriendsConstants.FRIENDS_MODELPACKAGE);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getJpaTransactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
