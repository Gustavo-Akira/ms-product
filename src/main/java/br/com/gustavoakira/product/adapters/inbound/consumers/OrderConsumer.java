package br.com.gustavoakira.product.adapters.inbound.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class OrderConsumer {

    @RabbitListener()
    public void consume(){

    }
}
