package br.com.gustavoakira.product.adapters.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {




    @Value("${rabbitmq.order}")
    private String order;

    @Value("${rabbitmq.product}")
    private String product;

    @Bean
    public Queue queue(){
        return new Queue(order,true);
    }

    @Bean
    public Queue queue1(){
        return new Queue(product,true);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
