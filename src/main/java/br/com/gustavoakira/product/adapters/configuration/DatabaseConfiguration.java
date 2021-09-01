package br.com.gustavoakira.product.adapters.configuration;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.converter.ProductReadConverter;
import br.com.gustavoakira.product.adapters.outbound.persistence.entities.converter.WriteConverter;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    @Value("{db.config.username}")
    private String username;

    @Value("{db.config.password}")
    private String password;

    @Value("{db.config.host}")
    private String host;

    @Value("{db.config.port}")
    private String port;

    @Value("{db.config.database}")
    private String database;

    @Bean
    public ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory){
        ConnectionFactoryInitializer factoryInitializer = new ConnectionFactoryInitializer();
        factoryInitializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator databasePopulator = new CompositeDatabasePopulator();
        databasePopulator.addPopulators(new ResourceDatabasePopulator( new ClassPathResource("schema.sql")));
        factoryInitializer.setDatabasePopulator(databasePopulator);
        return factoryInitializer;
    }



    @Override
    protected List<Object> getCustomConverters() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add( new ProductReadConverter());
        converterList.add(new WriteConverter());
        return List.copyOf(converterList);
    }

    @Override
    public ConnectionFactory connectionFactory() {
         return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(host)
                        .port(Integer.parseInt(port))
                        .username(username)
                        .password(password)
                        .database(database)
                        .build());
    }
}
