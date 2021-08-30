package br.com.gustavoakira.product.adapters.configuration;

import io.r2dbc.spi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;


@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration{

    @Bean
    public ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory){
        ConnectionFactoryInitializer factoryInitializer = new ConnectionFactoryInitializer();
        factoryInitializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator databasePopulator = new CompositeDatabasePopulator();
        databasePopulator.addPopulators(new ResourceDatabasePopulator( new ClassPathResource("schema.sql")));
        factoryInitializer.setDatabasePopulator(databasePopulator);
        return factoryInitializer;
    }

}
