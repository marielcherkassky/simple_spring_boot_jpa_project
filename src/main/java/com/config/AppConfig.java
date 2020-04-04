package com.config;

import com.DTO.ProductGetDTO;
import com.dao.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
/*
Use @Configuration annotation on top of any class to declare that this class provides one
or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

*/
@EnableJpaRepositories("com.repositories")
@EntityScan("com.dao")
/*
The @EnableJpaRepositories annotation tells Spring to scan for repository classes under the package repos.
When a repository class is found, Spring will generate an appropriate proxy class at runtime to provide implementation details.
So the @EnableJpaRepositories annotation is required to enable Spring Data JPA in a Spring application.
And in this configuration class, we create two important beans: LocalEntityManagerFactoryBean and JpaTransactionManager.
The first one sets up an EntityManagerFactory to work with the persistence unit named marieldb.
And the second one sets up a transaction manager for the configured EntityManagerFactory, in order to add transaction
 capababilities for respositories. Since we're creating a simple example, we don't use the @EnableTransactionManagement annotation.
 */

public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("marieldb");
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }


    @Bean
    public ModelMapper modelMapper() {

      ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Product.class, ProductGetDTO.class)
                .addMappings(m -> m.map(src -> src.getManufacturer().getId(), ProductGetDTO::setManufacturer));
      return mapper;

    }
    }


