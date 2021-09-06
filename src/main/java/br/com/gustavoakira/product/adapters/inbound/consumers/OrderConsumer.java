package br.com.gustavoakira.product.adapters.inbound.consumers;

import br.com.gustavoakira.ms.core.events.OrderMessage;
import br.com.gustavoakira.product.application.ports.ProductServicePort;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderConsumer {

    @Autowired
    private ProductServicePort port;

    @RabbitListener(queues = "ms-product")
    public void consume(OrderMessage orderMessage){
        System.out.println("Received message :"+orderMessage);
        orderMessage.getProducts().forEach((id,y)->{
            System.out.println(port.retireProducts(id,y).block());
        });
    }
}
